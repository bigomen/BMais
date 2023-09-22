package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.CoberturaRepository;
import com.bmais.gestao.restapi.restmodel.RestCoberturaPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.QueryHints;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class AuditorRepositoryCustomImpl  extends Repository<Auditor> implements AuditorRepositoryCustom
{

    private final CoberturaRepository coberturaRepository;

    public AuditorRepositoryCustomImpl(CoberturaRepository coberturaRepository) {
        this.coberturaRepository = coberturaRepository;
    }

    @Override
    public Optional<Auditor> pesquisarAuditor(Long id)
    {
        CriteriaQuery<Auditor> criteria = super.getCriteria();
        Root<Auditor> root = criteria.from(getClazz());
        root.fetch(Auditor_.USUARIO, JoinType.LEFT);
        root.fetch(Auditor_.TIPO_AUDITOR, JoinType.INNER);
        root.fetch(Auditor_.STATUS, JoinType.INNER);
        root.fetch(Auditor_.ENDERECO, JoinType.INNER);

        Predicate equalId = builder().equal(root.get(Auditor_.ID), id);
        criteria.where(equalId);
        TypedQuery<Auditor> typedQuery = super.entityManager.createQuery(criteria);
        Auditor auditor = null;
        try
        {
            auditor = typedQuery.getSingleResult();

        }catch (NoResultException nex)
        {
            return Optional.empty();
        }
        Collection<Vinculo> vinculos = pesquisarVinculosAuditor(auditor);
        auditor.setVinculos(vinculos);

        Collection<Documento> documentos = pesquisarDocumentosAuditor(auditor);
        auditor.setDocumentos(documentos);

        return Optional.of(auditor);
    }

    @Override
    public Collection<Auditor> findByPrestador(Prestador fornecedorAuditoria)
    {
        CriteriaQuery<Auditor> criteria = super.getCriteria();
        Root<Auditor> root = criteria.from(getClazz());
        Path<Long> id = root.get(Auditor_.ID);
        Path<String> nome = root.get(Auditor_.NOME);
        Path<String> documento = root.get(Auditor_.CRM_COREM);
        Path<TipoAuditor> tipo = root.get(Auditor_.TIPO_AUDITOR);
        Path<StatusAuditor> status = root.get(Auditor_.STATUS);

        Predicate equalFornecedor = builder().equal(root.get(Auditor_.PRESTADOR), fornecedorAuditoria);
        criteria.where(equalFornecedor);
        criteria.orderBy(builder().asc(nome));

        criteria.multiselect(id, nome, documento, tipo, status);
        TypedQuery<Auditor> query = super.entityManager.createQuery(criteria);
        return query.getResultList();
    }

    private Collection<Vinculo> pesquisarVinculosAuditor(Auditor auditor)
    {
        CriteriaQuery<Vinculo> query = builder().createQuery(Vinculo.class);
        Root<Auditor> root = query.from(getClazz());
        query.select(root.get(Auditor_.VINCULOS));
        query.where(builder().equal(root, auditor));
        TypedQuery<Vinculo> typedQuery = super.entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private Collection<Documento> pesquisarDocumentosAuditor(Auditor auditor)
    {
        CriteriaQuery<Documento> query = builder().createQuery(Documento.class);
        Root<Auditor> root = query.from(getClazz());
        query.select(root.get(Auditor_.DOCUMENTOS));
        query.where(builder().equal(root, auditor));
        TypedQuery<Documento> typedQuery = super.entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Auditor> listaAuditorCobertura(RestCoberturaPesquisa params) {
        CriteriaQuery<Auditor> criteria = super.getCriteria();
        Root<Auditor> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Join<Auditor, Vinculo> joinVinculos = root.join(Auditor_.VINCULOS);

        Path<Long> idAuditor = root.get(Auditor_.ID);
        Path<String> nomeAuditor = root.get(Auditor_.NOME);

        criteria.multiselect(idAuditor, nomeAuditor);

        Predicate dataInicio = builder().between(joinVinculos.get(Vinculo_.DATA_INICIO), params.getPeriodoInicial(), params.getPeriodoFinal());
        conjunction = builder().and(conjunction, dataInicio);
        Predicate dataFim = builder().between(joinVinculos.get(Vinculo_.DATA_FIM), params.getPeriodoInicial(), params.getPeriodoFinal());
        conjunction = builder().and(conjunction, dataFim);
        if(Objects.equals(params.getTipoAuditor(), "M")){
            Join<Auditor, TipoAuditor> joinTipo = root.join(Auditor_.TIPO_AUDITOR);
            Predicate equal = builder().equal(joinTipo.get(TipoAuditor_.ID), TipoAuditor.MEDICO);
            conjunction = builder().and(conjunction, equal);
        }
        if(Objects.equals(params.getTipoAuditor(), "E")){
            Join<Auditor, TipoAuditor> joinTipo = root.join(Auditor_.TIPO_AUDITOR);
            Predicate equal = builder().equal(joinTipo.get(TipoAuditor_.ID), TipoAuditor.ENFERMEIRO);
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getAuditor())){
            Predicate equal = builder().equal(root.get(Auditor_.ID), UtilSecurity.decryptId(params.getAuditor()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getCliente())){
            Join<Vinculo, Cliente> joinCliente = joinVinculos.join(Vinculo_.CLIENTE);
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getHospital())){
            Join<Vinculo, Hospital> joinHospital = joinVinculos.join(Vinculo_.HOSPITAL);
            Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getServico())){
            Join<Vinculo, Servico> joinServico = joinVinculos.join(Vinculo_.SERVICO);
            Predicate equal = builder().equal(joinServico.get(Servico_.ID), UtilSecurity.decryptId(params.getServico()));
            conjunction = builder().and(conjunction, equal);
        }

        criteria.where(conjunction);
        criteria.groupBy(idAuditor);

        TypedQuery<Auditor> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Auditor> getClazz()
    {
        return Auditor.class;
    }

	@Override
	public Collection<Auditor> pesquisarAuditorPorHospital(String hospital)
	{
		CriteriaQuery<Auditor> criteria = super.getCriteria();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    Root<Auditor> root = criteria.from(getClazz());
	    Join<Auditor, Vinculo> joinVinculos = root.join(Auditor_.VINCULOS, JoinType.INNER);
	    Join<Auditor, TipoAuditor> joinTipoAuditor = root.join(Auditor_.TIPO_AUDITOR);
	    
	    Path<Long> id = root.get(Auditor_.ID);
	    Path<String> nome = root.get(Auditor_.NOME);
	    Path<Long> idTipoAuditor = joinTipoAuditor.get(TipoAuditor_.ID);
	    Path<String> descTipoAuditor = joinTipoAuditor.get(TipoAuditor_.DESCRICAO);
	    
	    Predicate equalHospital = builder().equal(joinVinculos.get(Vinculo_.HOSPITAL).get(Hospital_.ID), UtilSecurity.decryptId(hospital));
	    criteria.multiselect(id, nome, idTipoAuditor, descTipoAuditor);
	    criteria.distinct(true);
	    criteria.where(equalHospital);
	    criteria.orderBy(cb.asc(nome));
	    TypedQuery<Auditor> typedQuery = entityManager.createQuery(criteria);
	    typedQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

	    
		return typedQuery.getResultList();
	}
}
