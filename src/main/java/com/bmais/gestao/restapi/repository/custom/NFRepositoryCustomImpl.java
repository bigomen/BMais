package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.NFItemRepository;
import com.bmais.gestao.restapi.restmodel.RestNFPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Repository
public class NFRepositoryCustomImpl extends com.bmais.gestao.restapi.repository.custom.Repository<NF> implements NFRepositoryCustom {

    private final NFItemRepository nfItemRepository;

    @Autowired
    public NFRepositoryCustomImpl(NFItemRepository nfItemRepository) {
        this.nfItemRepository = nfItemRepository;
    }

    @Override
    public Collection<NF> listar(RestNFPesquisa params){
        CriteriaQuery<NF> criteria = super.getCriteria();
        Root<NF> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Path<Long> idNF = root.get(NF_.ID);
        Path<String> numeroNota = root.get(NF_.NUMERO_NOTA);
        Join<NF, Empresa> joinEmpresa = root.join(NF_.EMPRESA);
        Path<Long> idEmpresa = joinEmpresa.get(Empresa_.ID);
        Path<String> razaoEmpresa = joinEmpresa.get(Empresa_.RAZAO_SOCIAL);
        Join<NF, Cliente> joinCliente = root.join(NF_.CLIENTE);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<Date> dataEmissao = root.get(NF_.DATA_EMISSAO);
        Path<Date> dataVencimento = root.get(NF_.DATA_VENCIMENTO);
        Path<String> servico = root.get(NF_.SERVICO);
        Path<StatusNF> status = root.get(NF_.STATUS);

        criteria.multiselect(idNF, numeroNota, idEmpresa, razaoEmpresa, idCliente, razaoCliente, dataEmissao, dataVencimento, servico, status);

        if(StringUtils.isNotBlank(params.getNumeroNota())){
            Predicate like = builder().like(builder().upper(root.get(NF_.NUMERO_NOTA)), like(params.getNumeroNota().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(StringUtils.isNotBlank(params.getEmpresa())){
            Join<NF, Empresa> joinNFEmpresa = root.join(NF_.EMPRESA, JoinType.INNER);
            Predicate equal = builder().equal(joinNFEmpresa.get(Empresa_.ID), UtilSecurity.decryptId(params.getEmpresa()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getCliente())){
            Join<NF, Cliente> joinNFCliente = root.join(NF_.CLIENTE, JoinType.INNER);
            Predicate equal = builder().equal(joinNFCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equal);
        }
        if(params.getDataInicioEmissao() != null && params.getDataFinalEmissao() != null){
            Predicate betweenData = builder().between(root.get(NF_.DATA_EMISSAO), params.getDataInicioEmissao(), params.getDataFinalEmissao());
            conjunction = builder().and(conjunction, betweenData);
        }
        if(params.getDataInicioVencimento() != null && params.getDataFinalVencimento() != null){
            Predicate betweenData = builder().between(root.get(NF_.DATA_VENCIMENTO), params.getDataInicioVencimento(), params.getDataFinalVencimento());
            conjunction = builder().and(conjunction, betweenData);
        }
        if(StringUtils.isNotBlank(params.getServico())){
            Predicate like = builder().like(builder().upper(root.get(NF_.SERVICO)), like(params.getServico().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Join<NF, StatusNF> joinStatusNF = root.join(NF_.STATUS, JoinType.INNER);
            Predicate equal = builder().equal(joinStatusNF.get(StatusNF_.ID), UtilSecurity.decryptId(params.getStatus()));
            conjunction = builder().and(conjunction, equal);
        }
        criteria.where(conjunction);
        TypedQuery<NF> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public NF detalhesNF(Long id){
        CriteriaQuery<NF> criteria = super.getCriteria();
        Root<NF> root = criteria.from(getClazz());

        Join<NF, Empresa> joinEmpresa = root.join(NF_.EMPRESA);
        Join<NF, Cliente> joinCliente = root.join(NF_.CLIENTE);
        Join<NF, DadosBancarios> joinDadosBancarios = root.join(NF_.DADOS_BANCARIOS);

        Path<Long> idNF = root.get(NF_.ID);
        Predicate equal = builder().equal(idNF, id);

        Path<Long> idEmpresa = joinEmpresa.get(Empresa_.ID);
        Path<String> razaoEmpresa = joinEmpresa.get(Empresa_.RAZAO_SOCIAL);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<Long> idDadosBancarios = joinDadosBancarios.get(DadosBancarios_.ID);
        Path<Banco> banco = joinDadosBancarios.get(DadosBancarios_.BANCO);
        Path<String> agencia = joinDadosBancarios.get(DadosBancarios_.AGENCIA);
        Path<String> conta = joinDadosBancarios.get(DadosBancarios_.CONTA);
        Path<Date> dataEmissao = root.get(NF_.DATA_EMISSAO);
        Path<Date> dataVencimento = root.get(NF_.DATA_VENCIMENTO);
        Path<String> servico = root.get(NF_.SERVICO);
        Path<String> numeroConta = root.get(NF_.NUMERO_NOTA);
        Path<StatusNF> status = root.get(NF_.STATUS);
        Path<String> natureza = root.get(NF_.NATUREZA);
        Path<String> fatura = root.get(NF_.FATURA);
        Path<String> mensagem = root.get(NF_.MENSAGEM);
        Path<BigDecimal> acrescimo = root.get(NF_.ACRESCIMO);
        Path<BigDecimal> desconto = root.get(NF_.DESCONTO);
        Path<Date> dataPagamento = root.get(NF_.DATA_PAGAMENTO);

        criteria.multiselect(idNF, idEmpresa, razaoEmpresa, idCliente, razaoCliente, idDadosBancarios, banco, agencia, conta,
                dataEmissao, dataVencimento, servico, numeroConta, status, natureza, fatura, mensagem, acrescimo, desconto,
                dataPagamento);

        criteria.where(equal);
        TypedQuery<NF> typedQuery = entityManager.createQuery(criteria);
        NF nf = typedQuery.getSingleResult();
        Collection<NFItem> nfItems = nfItemRepository.findByNf(nf);
        nf.setNfItems(nfItems);
        return nf;
    }

    @Override
    public Collection<NF> pesquisarNotasFiscais(Long empresa, Long dadosBancarios)
    {
        CriteriaQuery<NF> criteria = super.getCriteria();
        Root<NF> root = criteria.from(getClazz());
        root.fetch(NF_.NF_ITEMS, JoinType.INNER);
        Predicate where = builder().equal(root.get(NF_.STATUS).get(StatusNF_.ID), 2L);

        if(empresa != null)
        {
            Predicate equalEmpresa = builder().equal(root.get(NF_.EMPRESA).get(Empresa_.ID), empresa);
            where = builder().and(where, equalEmpresa);
        }

        if(dadosBancarios != null)
        {
            Predicate equalDadosBancarios = builder().equal(root.get(NF_.DADOS_BANCARIOS).get(DadosBancarios_.ID), dadosBancarios);
            where = builder().and(where, equalDadosBancarios);
        }

        criteria.where(where);
        criteria.distinct(true);
        TypedQuery<NF> typedQuery = entityManager.createQuery(criteria);
        typedQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        return typedQuery.getResultList();
    }


    @Override
    public Class<NF> getClazz(){return NF.class;}
}
