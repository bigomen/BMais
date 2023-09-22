package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.Fornecedor;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica;
import com.bmais.gestao.restapi.repository.FornecedorRepository;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import com.bmais.gestao.restapi.restmodel.RestFornecedor;
import com.bmais.gestao.restapi.restmodel.RestFornecedorPesquisa;
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
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final DocumentoService documentoService;
    private final DadosBancariosService dadosBancariosService;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, DocumentoService documentoService, DadosBancariosService dadosBancariosService){
        super();
        this.fornecedorRepository = fornecedorRepository;
        this.documentoService = documentoService;
        this.dadosBancariosService = dadosBancariosService;
    }

    public Collection<RestFornecedor> listar(RestFornecedorPesquisa params)
    {
        Collection<Fornecedor> fornecedores = fornecedorRepository.pesquisarFornecedor(params);
        return fornecedores.stream().map(Fornecedor::modelParaRest).collect(Collectors.toList());
    }

    public RestFornecedor detalhes(String id)
    {
        if(!fornecedorRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Fornecedor fornecedorServicoProduto = fornecedorRepository.detalhes(UtilSecurity.decryptId(id));
        return fornecedorServicoProduto.modelParaRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestFornecedor restFornecedor)
    {
        Fornecedor fornecedorServicoProduto = fornecedorRepository.findByRazaoSocial(restFornecedor.getRazaoSocial());
        if(fornecedorServicoProduto != null){
            throw new ObjectAlreadyInBase(MensagensID.RZ + restFornecedor.getRazaoSocial() + MensagensID.JaCadastrado);
        }
        fornecedorServicoProduto = fornecedorRepository.findByCnpj(restFornecedor.getCnpj());
        if(fornecedorServicoProduto != null){
            throw new ObjectAlreadyInBase(MensagensID.CN + restFornecedor.getRazaoSocial() + MensagensID.JaCadastrado);
        }
        restFornecedor.setDataInclusao(UtilData.obterDataAtual());
        fornecedorServicoProduto = restFornecedor.restParaModel();

        if(Objects.nonNull(fornecedorServicoProduto.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(fornecedorServicoProduto.getDadosBancarios());
        }

        fornecedorServicoProduto = fornecedorRepository.save(fornecedorServicoProduto);

        fornecedorServicoProduto.getDocumentos().forEach(doc -> {
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Fornecedor.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
        });

        fornecedorRepository.save(fornecedorServicoProduto);

        if(!fornecedorServicoProduto.getDocumentos().isEmpty())
        {
            documentoService.upload(Fornecedor.PASTA_DOCUMENTOS, fornecedorServicoProduto.getDocumentos());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestFornecedor restFornecedor, String id){
        Fornecedor fornecedorServicoProduto = fornecedorRepository.findById(UtilSecurity.decryptId(id)).orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR032));

        restFornecedor.setId(id);
        Fornecedor novoFornecedor = restFornecedor.restParaModel();

        if(Objects.nonNull(novoFornecedor.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(novoFornecedor.getDadosBancarios());
        }

        if(fornecedorRepository.validarRazaoCnpj(novoFornecedor.getRazaoSocial(), novoFornecedor.getCnpj(), novoFornecedor.getId())){
            throw new ObjectAlreadyInBase("Razao/CNPJ já utilizados por outro fornecedor");
        }
        novoFornecedor.getDocumentos().removeIf(doc -> doc.getId() == null);

        for (RestDocumento doc : restFornecedor.getDocumentos())
        {
            if(doc.getId() == null)
            {
                doc.setIdentificador(novoFornecedor.getCnpj());
                Documento documento = documentoService.salvarDocumento(doc);
                documento.setImagem(doc.getImagem());
                novoFornecedor.addDocumentos(documento);
            }
        }

        
        novoFornecedor.getDocumentos()
        .stream()
        .filter(doc -> StringUtils.isNotBlank(doc.getImagem()))
        .forEach(doc ->{
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Fornecedor.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
            documentoService.upload(Fornecedor.PASTA_DOCUMENTOS, doc.getDescricao().substring(doc.getDescricao().lastIndexOf("/") + 1), doc.getImagem());
        });

        fornecedorRepository.save(novoFornecedor);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(UtilSecurity.decryptId(id));
        if(fornecedor.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.PTR032);
        }
        fornecedor.get().setStatus(new StatusPessoaJuridica(StatusPessoaJuridica.EXCLUIDO));
        fornecedorRepository.save(fornecedor.get());
    }
}
