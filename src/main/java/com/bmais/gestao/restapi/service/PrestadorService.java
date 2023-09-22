package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica;
import com.bmais.gestao.restapi.repository.PrestadorRepository;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import com.bmais.gestao.restapi.restmodel.RestPrestador;
import com.bmais.gestao.restapi.restmodel.RestPrestadorPesquisa;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PrestadorService {

    private final PrestadorRepository prestadorRepository;
    private final DocumentoService documentoService;
    private final DadosBancariosService dadosBancariosService;
    private final AuditorService auditorService;

    @Autowired
    public PrestadorService(PrestadorRepository prestadorRepository, DocumentoService documentoService, DadosBancariosService dadosBancariosService, AuditorService auditorService)
    {
        super();
        this.prestadorRepository = prestadorRepository;
        this.documentoService = documentoService;
        this.dadosBancariosService = dadosBancariosService;
        this.auditorService = auditorService;
    }

    public Collection<RestPrestador> listar(RestPrestadorPesquisa params){
        Collection<Prestador> prestadores = prestadorRepository.pesquisarPrestador(params);
        return prestadores.stream().map(Prestador::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestPrestador> listaSimples(){
        Collection<Prestador> prestadores = prestadorRepository.listaSimples();
        return prestadores.stream().map(Prestador::modelParaRest).collect(Collectors.toList());
    }

    public RestPrestador detalhes(String id){
        Optional<Prestador> optionalFornecedorAuditoria = prestadorRepository.detalhes(UtilSecurity.decryptId(id));
        Prestador fornecedorAuditoria = optionalFornecedorAuditoria.orElseThrow(() -> new ObjectNotFoundException("Prestador não localizado"));
        return fornecedorAuditoria.modelParaRest();
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestPrestador restFornecedorAuditoria)
    {
        Prestador fornecedorAuditoria = prestadorRepository.existsByRazaoSocial(restFornecedorAuditoria.getRazaoSocial());
        if(fornecedorAuditoria != null){
            throw new ObjectAlreadyInBase(MensagensID.RZ + restFornecedorAuditoria.getRazaoSocial() + MensagensID.JaCadastrado);
        }
        fornecedorAuditoria = prestadorRepository.existsByCnpj(restFornecedorAuditoria.getCnpj());
        if(fornecedorAuditoria != null){
            throw new ObjectNotFoundException(MensagensID.CN + restFornecedorAuditoria.getCnpj() + MensagensID.JaCadastrado);
        }
        restFornecedorAuditoria.setDataInclusao(UtilData.obterDataAtual());
        Prestador prestador = restFornecedorAuditoria.restParaModel();

        if(Objects.nonNull(prestador.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(prestador.getDadosBancarios());
        }

        prestador = prestadorRepository.save(prestador);

        prestador.getDocumentos().forEach(doc -> {
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Prestador.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
        });


        prestadorRepository.save(prestador);

        if(!prestador.getDocumentos().isEmpty())
        {
            documentoService.upload(Prestador.PASTA_DOCUMENTOS, prestador.getDocumentos());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestPrestador restFornecedorAuditoria, String id)
    {
        Prestador prestador = prestadorRepository.findById(UtilSecurity.decryptId(id)).orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR032));

        if(prestadorRepository.validarCnpj(restFornecedorAuditoria.getCnpj(), restFornecedorAuditoria.getRazaoSocial(), UtilSecurity.decryptId(id))){
            throw new ObjectAlreadyInBase("Razão Social/CNPJ já utilizado por outro prestador");
        }

        restFornecedorAuditoria.setId(id);
        Prestador novoPrestador = restFornecedorAuditoria.restParaModel();

        if(Objects.nonNull(novoPrestador.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(novoPrestador.getDadosBancarios());
        }

        novoPrestador.getDocumentos().removeIf(doc -> doc.getId() == null);

        for (RestDocumento doc : restFornecedorAuditoria.getDocumentos())
        {
            if(doc.getId() == null)
            {
                doc.setIdentificador(novoPrestador.getCnpj());
                Documento documento = documentoService.salvarDocumento(doc);
                documento.setImagem(doc.getImagem());
                novoPrestador.addDocumentos(documento);
            }
        }
        
        novoPrestador.getDocumentos()
        .stream()
        .filter(doc -> StringUtils.isNotBlank(doc.getImagem()))
        .forEach(doc ->{
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Prestador.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
            documentoService.upload(Prestador.PASTA_DOCUMENTOS, doc.getDescricao().substring(doc.getDescricao().lastIndexOf("/") + 1), doc.getImagem());
        });

        if(Objects.nonNull(novoPrestador.getDataFim())){
            auditorService.inativarAuditoresPrestador(prestador.getId());
            novoPrestador.setStatus(new StatusPessoaJuridica(StatusPessoaJuridica.INATIVO));
        }else {
            novoPrestador.setStatus(new StatusPessoaJuridica(StatusPessoaJuridica.ATIVO));
        }

        prestadorRepository.save(novoPrestador);


    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        Optional<Prestador> prestador = prestadorRepository.findById(UtilSecurity.decryptId(id));
        if(prestador.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.PTR032);
        }
        prestador.get().setStatus(new StatusPessoaJuridica((StatusPessoaJuridica.EXCLUIDO)));
        prestadorRepository.save(prestador.get());
    }
}
