package com.bmais.gestao.restapi.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Auditor_;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Cliente_;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.model.Cobertura_;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.Hospital_;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.Servico_;
import com.bmais.gestao.restapi.model.StatusAuditor;
import com.bmais.gestao.restapi.model.StatusAuditor_;
import com.bmais.gestao.restapi.model.TipoAuditor;
import com.bmais.gestao.restapi.model.TipoAuditor_;
import com.bmais.gestao.restapi.model.Vinculo;
import com.bmais.gestao.restapi.model.Vinculo_;
import com.bmais.gestao.restapi.model.projections.EscalaProjection;
import com.bmais.gestao.restapi.repository.custom.Repository;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

public class VinculosRepositoryCustomImpl extends Repository<Vinculo>	implements VinculosRepositoryCustom
{
	@Override
	public Collection<EscalaProjection> pesquisarEscalas(RestEscalaPesquisa param)
	{
		CriteriaQuery<EscalaProjection> criteria = builder().createQuery(EscalaProjection.class);
		Root<Vinculo> root = criteria.from(Vinculo.class);
		Join<Vinculo, Cliente> joinCliente = root.join(Vinculo_.CLIENTE, JoinType.INNER);
		Join<Vinculo, Hospital> joinHospital = root.join(Vinculo_.HOSPITAL, JoinType.INNER);
		Join<Vinculo, Auditor> joinAuditor = root.join(Vinculo_.AUDITOR, JoinType.INNER);
		Join<Auditor, StatusAuditor> joinStatusAuditor = joinAuditor.join(Auditor_.STATUS, JoinType.INNER);
		Join<Auditor, TipoAuditor> joinTipoAuditor = joinAuditor.join(Auditor_.TIPO_AUDITOR, JoinType.INNER);
		Join<Vinculo, Servico> joinServico = root.join(Vinculo_.SERVICO, JoinType.INNER);
		
		Path<Long> id = root.get(Vinculo_.ID);
		Path<LocalDate> dataInicio = root.get(Vinculo_.DATA_INICIO);
		Path<LocalDate> dataFim = root.get(Vinculo_.DATA_FIM);
		Path<String> cliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
		Path<String> hospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
		Path<String> codServico = joinServico.get(Servico_.CODIGO);
		Path<String> servico = joinServico.get(Servico_.DESCRICAO);
		Path<String> nomeAuditor = joinAuditor.get(Auditor_.NOME);
		Path<Long> idTipoAuditor = joinTipoAuditor.get(TipoAuditor_.ID);
		Path<String> tipoAuditor = joinTipoAuditor.get(TipoAuditor_.DESCRICAO);
		Path<String> emailAuditor = joinAuditor.get(Auditor_.EMAIL);
		Path<String> documentoAuditor = joinAuditor.get(Auditor_.CRM_COREM);
		Path<String> statusAuditor = joinStatusAuditor.get(StatusAuditor_.DESCRICAO);
		
		Expression<Date> expressionDataFim = builder().coalesce(root.get(Vinculo_.DATA_FIM), builder().currentDate());
		Predicate periodoAtuacao = builder().between(builder().currentDate(), root.get(Vinculo_.DATA_INICIO), expressionDataFim);
		Predicate where = filters(param, joinCliente, joinHospital, joinAuditor, joinServico);
		where = builder().and(where, periodoAtuacao);
		
		criteria.multiselect(id, cliente, hospital, codServico, servico, nomeAuditor, idTipoAuditor, tipoAuditor, emailAuditor, documentoAuditor, statusAuditor, dataInicio, dataFim);
		
		criteria.where(where);
		criteria.orderBy(builder().asc(nomeAuditor));
		TypedQuery<EscalaProjection> typedQuery = entityManager.createQuery(criteria);
		
		return typedQuery.getResultList();
	}

	@Override
	public Class<Vinculo> getClazz()
	{
		return Vinculo.class;
	}

	@Override
	public Collection<EscalaProjection> pesquisarCoberturas(
			RestEscalaPesquisa param)
	{
		CriteriaQuery<EscalaProjection> criteria = builder().createQuery(EscalaProjection.class);
		Root<Vinculo> root = criteria.from(Vinculo.class);
		Join<Vinculo, Cliente> joinCliente = root.join(Vinculo_.CLIENTE, JoinType.INNER);
		Join<Vinculo, Hospital> joinHospital = root.join(Vinculo_.HOSPITAL, JoinType.INNER);
		Join<Vinculo, Auditor> joinAuditor = root.join(Vinculo_.AUDITOR, JoinType.INNER);
		Join<Auditor, StatusAuditor> joinStatusAuditor = joinAuditor.join(Auditor_.STATUS, JoinType.INNER);
		Join<Auditor, TipoAuditor> joinTipoAuditor = joinAuditor.join(Auditor_.TIPO_AUDITOR, JoinType.INNER);
		Join<Vinculo, Servico> joinServico = root.join(Vinculo_.SERVICO, JoinType.INNER);
		Join<Vinculo, Cobertura> joinCobertura = root.join(Vinculo_.COBERTURAS, JoinType.INNER);
		Join<Cobertura, Auditor> joinAuditorCobertura = joinCobertura.join(Cobertura_.AUDITOR, JoinType.INNER);
		
		Path<Long> id = root.get(Vinculo_.ID);
		Path<LocalDate> dataInicio = root.get(Vinculo_.DATA_INICIO);
		Path<LocalDate> dataFim = root.get(Vinculo_.DATA_FIM);
		Path<String> cliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
		Path<String> hospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
		Path<String> codServico = joinServico.get(Servico_.CODIGO);
		Path<String> servico = joinServico.get(Servico_.DESCRICAO);
		Path<String> nomeAuditor = joinAuditor.get(Auditor_.NOME);
		Path<Long> idTipoAuditor = joinTipoAuditor.get(TipoAuditor_.ID);
		Path<String> tipoAuditor = joinTipoAuditor.get(TipoAuditor_.DESCRICAO);
		Path<String> emailAuditor = joinAuditor.get(Auditor_.EMAIL);
		Path<String> documentoAuditor = joinAuditor.get(Auditor_.CRM_COREM);
		Path<String> statusAuditor = joinStatusAuditor.get(StatusAuditor_.DESCRICAO);
		Path<Long> idCobertura = joinCobertura.get(Cobertura_.ID);
		Path<LocalDate> inicioCobertura = joinCobertura.get(Cobertura_.PERIODO_INICIO);
		Path<LocalDate> fimCobertura = joinCobertura.get(Cobertura_.PERIODO_FIM);
		Path<String> obsCobertura = joinCobertura.get(Cobertura_.OBSERVACAO);
		Path<Long> idAuditorCobertura = joinAuditorCobertura.get(Auditor_.ID);
		Path<String> nomeAuditorCobertura = joinAuditorCobertura.get(Auditor_.NOME);
		Path<String> emailAuditorCobertura = joinAuditorCobertura.get(Auditor_.EMAIL);
		Path<String> docAuditorCobertura = joinAuditorCobertura.get(Auditor_.CRM_COREM);
		
		Expression<LocalDate> dataInicioCobertura = builder().literal(param.getDataInicio());
		Expression<LocalDate> dataFimCobertura = builder().literal(param.getDataFim());
		
		Predicate inicioPeriodo = builder().greaterThanOrEqualTo(joinCobertura.get(Cobertura_.PERIODO_INICIO), dataInicioCobertura);
		Predicate fimPeriodo = builder().lessThanOrEqualTo(joinCobertura.get(Cobertura_.PERIODO_FIM), dataFimCobertura);
		Predicate where = builder().and(inicioPeriodo, fimPeriodo);
		Predicate filters = filters(param, joinCliente, joinHospital, joinAuditor, joinServico);
		where = builder().and(where, filters);

		criteria.multiselect(id, cliente, hospital, codServico, servico, nomeAuditor, idTipoAuditor, tipoAuditor, emailAuditor, documentoAuditor, statusAuditor, dataInicio, dataFim,
				idCobertura, inicioCobertura, fimCobertura, obsCobertura, idAuditorCobertura, nomeAuditorCobertura, emailAuditorCobertura, docAuditorCobertura);
		
		criteria.where(where);
		criteria.orderBy(builder().asc(nomeAuditor));
		TypedQuery<EscalaProjection> typedQuery = entityManager.createQuery(criteria);
		
		return typedQuery.getResultList();
	}

	private Predicate filters(RestEscalaPesquisa param,
			Join<Vinculo, Cliente> joinCliente,
			Join<Vinculo, Hospital> joinHospital,
			Join<Vinculo, Auditor> joinAuditor,
			Join<Vinculo, Servico> joinServico)
	{
		Predicate conjunction = builder().conjunction();
		
		if(StringUtils.isNotBlank(param.getAuditor()))
		{
			Predicate equalAuditor = builder().equal(joinAuditor.get(Auditor_.ID), UtilSecurity.decryptId(param.getAuditor()));
			conjunction = builder().and(conjunction, equalAuditor);
		}
		
		if(StringUtils.isNotBlank(param.getCliente()))
		{
			Predicate equalCliente = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(param.getCliente()));
			conjunction = builder().and(conjunction, equalCliente);
		}
		
		if(StringUtils.isNotBlank(param.getServico()))
		{
			Predicate equalServico = builder().equal(joinServico.get(Servico_.ID), UtilSecurity.decryptId(param.getServico()));
			conjunction = builder().and(conjunction, equalServico);
		}
		
		if(StringUtils.isNotBlank(param.getHospital()))
		{
			Predicate equalHospital = builder().equal(joinHospital.get(Auditor_.ID), UtilSecurity.decryptId(param.getHospital()));
			conjunction = builder().and(conjunction, equalHospital);
		}
		
		if(StringUtils.isNotBlank(param.getTipoAuditor()))
		{
			Predicate equalTipoAuditor = builder().equal(joinAuditor.get(Auditor_.TIPO_AUDITOR).get(TipoAuditor_.ID), Long.parseLong(param.getTipoAuditor()));
			conjunction = builder().and(conjunction, equalTipoAuditor);
		}
		return conjunction;
	}
}
