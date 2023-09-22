package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.CadastroJaExistente;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.MandatoryAttribute;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.mapper.ClienteMapper;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.repository.ClienteRepository;
import com.bmais.gestao.restapi.repository.ClienteServicosRepository;
import com.bmais.gestao.restapi.repository.UsuarioRepository;
import com.bmais.gestao.restapi.restmodel.*;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteServicosRepository clienteServicosRepository;
    private final DocumentoService documentoService;
    private final UsuarioRepository usuarioRepository;
    private final DadosBancariosService dadosBancariosService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteServicosRepository clienteServicosRepository,
                          DocumentoService documentoService, UsuarioRepository usuarioRepository, DadosBancariosService dadosBancariosService) {
        super();
        this.clienteRepository = clienteRepository;
        this.clienteServicosRepository = clienteServicosRepository;
        this.documentoService = documentoService;
        this.usuarioRepository = usuarioRepository;
        this.dadosBancariosService = dadosBancariosService;
    }

    public RestCliente detalhes(String id){
        Cliente cliente = clienteRepository.pesquisarCliente(UtilSecurity.decryptId(id)).orElseThrow(() ->new ObjectNotFoundException(MensagensID.PTR032));
        RestCliente restCliente = cliente.modelParaFullRest();
        Optional<String> logotipo = documentoService.download(cliente.getLogotipo());

        logotipo.ifPresent(logo -> {
            String fileMimeType = documentoService.fileMimeType(cliente.getLogotipo());
            restCliente.setLogotipo(fileMimeType + logo);
        });

        return restCliente;
    }

    public Collection<RestCliente> listaSimples(){
        Collection<Cliente> clientes = clienteRepository.listaSimples();
        return clientes.stream().map(ClienteMapper.INSTANCE::convertToListaSimples).collect(Collectors.toList());
    }
    
    public Collection<RestCliente> pesquisarClientesVinculos()
    {
    	Collection<Cliente> clientes = clienteRepository.pesquisarClientes();
    	return clientes.stream().map(Cliente::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestCliente> listar(RestPessoaJuridicaPesquisa params) {
        Collection<Cliente> clientes = clienteRepository.pesquisarCliente(params);
        return clientes.stream().map(Cliente::modelParaRest).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestCliente restCliente)
    {
        if(!validarDatasServicos(restCliente.getServicos())){
            throw new IncorrectData("Não é permitido um servico compreender o periodo de outro");
        }
        if(!validarHospitalClienteServico(restCliente.getHospitais())){
            throw new IncorrectData("Não é permitido cadastrar mais de uma vez uma mesma combinação de hospital e serviço");
        }
        boolean existeRazaoSocial = clienteRepository.existsByRazaoSocial(restCliente.getRazaoSocial());
        if(existeRazaoSocial){
            throw new CadastroJaExistente(restCliente.getRazaoSocial() + MensagensID.JaCadastrado);
        }

        boolean existsByCnpj = clienteRepository.existsByCnpj(restCliente.getCnpj());
        if(existsByCnpj){
            throw new CadastroJaExistente(restCliente.getCnpj() + MensagensID.JaCadastrado);
        }
        restCliente.setDataInclusao(UtilData.obterDataAtual());

        Cliente cliente = restCliente.restParaModel();

        if(Objects.nonNull(cliente.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(cliente.getDadosBancarios());
        }

        if(StringUtils.isNotBlank(restCliente.getLogotipo()))
        {
            String extension = documentoService.fileExtension(restCliente.getLogotipo());
            String nome = "LOGO_".concat(restCliente.getCnpj()).concat(extension);
            cliente.setLogotipo(Cliente.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Cliente.PASTA_DOCUMENTOS, nome, restCliente.getLogotipo());
        }
        cliente = clienteRepository.save(cliente);
        if(cliente.getUsuarios() != null){
            cliente.getUsuarios().forEach(u -> {
                usuarioRepository.associaUsuarioCliente(u.getCliente().getId(), u.getId());
            });
        }
        cliente.getDocumentos().forEach(doc -> {
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Cliente.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
        });

        clienteRepository.save(cliente);

        if(!cliente.getDocumentos().isEmpty())
        {
            documentoService.upload(Cliente.PASTA_DOCUMENTOS, cliente.getDocumentos());
        }
    }

    private Boolean validarHospitalClienteServico(Collection<RestHospitalCliente> hospitalCliente){
        AtomicReference<Boolean> valido = new AtomicReference<>(true);
        if(hospitalCliente != null){
            Collection<RestHospitalCliente> restHospitalClientes = new ArrayList<>(hospitalCliente);
            for(RestHospitalCliente hc : hospitalCliente){
                restHospitalClientes.remove(hc);
                restHospitalClientes.forEach(hosCli -> {
                    if(Objects.equals(hosCli.getHospital().getId(), hc.getHospital().getId())
                            && Objects.equals(hosCli.getServico().getId(), hc.getServico().getId())){
                        valido.set(false);
                    }
                });
            }
        }
        return valido.get();
    }

    private Boolean validarDatasServicos(Collection<RestClienteServico> clienteServicos){
        AtomicReference<Boolean> valido = new AtomicReference<>(true);

        if(clienteServicos != null){
            Collection<RestClienteServico> restClienteServicos = new ArrayList<>(clienteServicos);
            for(RestClienteServico cs: clienteServicos){
                restClienteServicos.remove(cs);
                restClienteServicos.forEach(cliSer -> {
                    if(cs.getDataInicio() == null || cliSer.getDataInicio() == null){
                        throw new MandatoryAttribute("Servicos - Data Inicio");
                    }
                    if(cliSer.getServico().getId().equals(cs.getServico().getId())){
                        if(cliSer.getDataFim() == null && cs.getDataFim() == null){
                            throw new IncorrectData("Dois ou mais serviços iguais com a data fim vazia");
                        }else if(cliSer.getDataFim() == null){
                            if(cs.getDataInicio().isAfter(cliSer.getDataInicio()) || cs.getDataFim().isAfter(cliSer.getDataInicio())){
                                valido.set(false);
                            }
                        }else if(cs.getDataFim() == null){
                            if(cliSer.getDataInicio().isAfter(cs.getDataInicio()) || cliSer.getDataFim().isAfter(cs.getDataInicio())){
                                valido.set(false);
                            }
                        }else if(cs.getDataInicio().isAfter(cliSer.getDataInicio()) && cs.getDataInicio().isBefore(cliSer.getDataFim())){
                            valido.set(false);
                        }else if(cs.getDataFim().isAfter(cliSer.getDataInicio()) && cs.getDataFim().isBefore(cliSer.getDataFim())){
                            valido.set(false);
                        }
                    }
                });
            }
        }
        return valido.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RestCliente restCliente, String id)
    {
        if(!validarDatasServicos(restCliente.getServicos())){
            throw new IncorrectData("Não é permitido um servico compreender o periodo de outro");
        }
        if(!validarHospitalClienteServico(restCliente.getHospitais())){
            throw new IncorrectData("Não é permitido cadastrar mais de uma vez uma mesma combinação de hospital e serviço");
        }

        if(clienteRepository.razaoCnpjValido(restCliente.getRazaoSocial(), restCliente.getCnpj(), UtilSecurity.decryptId(id))){
            throw new CadastroJaExistente("Razão Social/CNPJ");
        }

        Cliente cliente = clienteRepository.findById(UtilSecurity.decryptId(id)).orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR032));

        if(StringUtils.isNotBlank(cliente.getLogotipo()))
        {
            documentoService.excluir(cliente.getLogotipo());
        }

        clienteServicosRepository.excluirServicos(cliente);

        restCliente.setId(id);
        restCliente.setUsuarios(null);
        Cliente novoCliente = restCliente.restParaModel();
        Collection<Long> usuariosIds = usuarioRepository.idsUsuarioCliente(novoCliente.getId());
        if(Objects.nonNull(usuariosIds)){
            for(Long userId : usuariosIds){
                Usuario usuario = new Usuario(userId);
                novoCliente.getUsuarios().add(usuario);
            }
        }

        if(Objects.nonNull(novoCliente.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(novoCliente.getDadosBancarios());
        }

        novoCliente.getDocumentos().removeIf(doc -> doc.getId() == null);

        for (RestDocumento doc : restCliente.getDocumentos())
        {
            if(doc.getId() == null)
            {
                doc.setIdentificador(novoCliente.getCnpj());
                Documento documento = documentoService.salvarDocumento(doc);
                documento.setImagem(doc.getImagem());
                novoCliente.addDocumentos(documento);
            }
        }

        if(StringUtils.isNotBlank(restCliente.getLogotipo()))
        {
            String extension = documentoService.fileExtension(restCliente.getLogotipo());
            String nome = "LOGO_".concat(restCliente.getCnpj()).concat(extension);
            novoCliente.setLogotipo(Cliente.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Cliente.PASTA_DOCUMENTOS, nome, restCliente.getLogotipo());
        }

        novoCliente.getDocumentos()
                .stream()
                .filter(doc -> StringUtils.isNotBlank(doc.getImagem()))
                .forEach(doc ->{
                    String docExtension = documentoService.fileExtension(doc.getImagem());
                    doc.setDescricao(Cliente.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
                    documentoService.upload(Cliente.PASTA_DOCUMENTOS, doc.getDescricao().substring(doc.getDescricao().lastIndexOf("/") + 1), doc.getImagem());
                });

        clienteRepository.save(novoCliente);
        usuarioRepository.limpaUsuarios(cliente.getId());
        if(cliente.getUsuarios() != null){
            cliente.getUsuarios().forEach(u -> {
                usuarioRepository.associaUsuarioCliente(u.getCliente().getId(), u.getId());
            });
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id) {
        Cliente cliente = clienteRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR032));
        cliente.excluir();
        clienteRepository.save(cliente);
    }

//    private Boolean validarExclusaoServico(Collection<ClienteServico> servicos, Collection<HospitalCliente> hospitaisServicos){
//
//    }
}
