package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.ContatoRepository;
import com.bmais.gestao.restapi.repository.DadosBancariosRepository;
import com.bmais.gestao.restapi.repository.DocumentoRepository;
import com.bmais.gestao.restapi.restmodel.RestFornecedorPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;

@org.springframework.stereotype.Repository
public class FornecedorRepositoryCustomImpl extends Repository<Fornecedor> implements FornecedorRepositoryCustom {

    private final DadosBancariosRepository dadosBancariosRepository;

    private final ContatoRepository contatoRepository;

    private final DocumentoRepository documentoRepository;

    @Autowired
    public FornecedorRepositoryCustomImpl(DadosBancariosRepository dadosBancariosRepository, ContatoRepository contatoRepository, DocumentoRepository documentoRepository){
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.contatoRepository = contatoRepository;
        this.documentoRepository = documentoRepository;
    }

    @Override
    public Collection<Fornecedor> pesquisarFornecedor(RestFornecedorPesquisa params){
        CriteriaQuery<Fornecedor> criteria = super.getCriteria();
        Root<Fornecedor> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Path<Long> idFornecedor = root.get(Fornecedor_.ID);
        Path<String> razaoSocial = root.get(Fornecedor_.RAZAO_SOCIAL);
        Path<String> cnpj = root.get(Fornecedor_.CNPJ);
        Path<RamoAtividade> ramoAtividade = root.get(Fornecedor_.RAMO_ATIVIDADE);
        Path<StatusPessoaJuridica> status = root.get(Fornecedor_.STATUS);

        criteria.multiselect(idFornecedor, razaoSocial, cnpj, ramoAtividade, status);

        if(StringUtils.isNotBlank(params.getRazaoSocial())){
            Predicate like = builder().like(builder().upper(root.get(Fornecedor_.RAZAO_SOCIAL)), like((params.getRazaoSocial().toUpperCase())));
            conjunction = builder().and(conjunction, like);
        }
        if(StringUtils.isNotBlank(params.getCnpj())){
            Predicate like = builder().like(builder().upper(root.get(Fornecedor_.CNPJ)), like(params.getCnpj().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(StringUtils.isNotBlank(params.getRamoAtividade())){
            Join<Fornecedor, RamoAtividade> joinRamoAtividade = root.join(Fornecedor_.RAMO_ATIVIDADE, JoinType.INNER);
            Predicate equal = builder().equal(joinRamoAtividade.get(RamoAtividade_.ID), UtilSecurity.decryptId(params.getRamoAtividade()));
            conjunction = builder().and(conjunction,equal);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Join<Fornecedor, StatusPessoaJuridica> joinStatusPessoaJuridica = root.join(PessoaJuridica_.STATUS, JoinType.INNER);
            Predicate equal = builder().equal(joinStatusPessoaJuridica.get(StatusPessoaJuridica_.ID), params.getStatus());
            conjunction = builder().and(conjunction, equal);
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(razaoSocial));
        TypedQuery<Fornecedor> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();

    }

    @Override
    public Fornecedor detalhes(Long id){
        CriteriaQuery<Fornecedor> criteria = super.getCriteria();
        Root<Fornecedor> root = criteria.from(getClazz());

        root.fetch(Fornecedor_.ENDERECO, JoinType.INNER);
        root.fetch(Fornecedor_.RAMO_ATIVIDADE, JoinType.INNER);
        root.fetch(Fornecedor_.STATUS, JoinType.INNER);

        criteria.where(builder().equal(root.get(Fornecedor_.ID), id));

        TypedQuery<Fornecedor> typedQuery = entityManager.createQuery(criteria);
        Fornecedor fornecedorServicoProduto = typedQuery.getSingleResult();
        fornecedorServicoProduto.setDadosBancarios(dadosBancariosRepository.dadosPorPessoaJuridica(fornecedorServicoProduto));
        fornecedorServicoProduto.setContatos(contatoRepository.pesquisarContatosPessoaJuridica(fornecedorServicoProduto));
        fornecedorServicoProduto.setDocumentos(documentoRepository.documentosPessoaJuridica(fornecedorServicoProduto));
        return fornecedorServicoProduto;
    }

    @Override
    public Class<Fornecedor> getClazz(){return  Fornecedor.class;}
}
