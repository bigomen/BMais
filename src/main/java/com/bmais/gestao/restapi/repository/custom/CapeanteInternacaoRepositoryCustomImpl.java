package com.bmais.gestao.restapi.repository.custom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bmais.gestao.restapi.model.*;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.model.CapeanteInternacao_;
import com.bmais.gestao.restapi.model.Cliente_;
import com.bmais.gestao.restapi.model.HospitalCliente_;
import com.bmais.gestao.restapi.model.Hospital_;
import com.bmais.gestao.restapi.model.Paciente_;
import com.bmais.gestao.restapi.model.ProntuarioCapeante_;
import com.bmais.gestao.restapi.model.Resumo_;
import com.bmais.gestao.restapi.model.StatusCapeante_;
import com.bmais.gestao.restapi.model.projections.CapeanteInternacaoProjection;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacaoPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

public class CapeanteInternacaoRepositoryCustomImpl extends Repository<CapeanteInternacao>	implements CapeanteInternacaoRepositoryCustom
{
	@Override
	public Class<CapeanteInternacao> getClazz()
	{
		return CapeanteInternacao.class;
	}

	@Override
	public Collection<CapeanteInternacaoProjection> listaInternacoes(RestCapeanteInternacaoPesquisa params)
	{
		CriteriaQuery<CapeanteInternacaoProjection> query = builder().createQuery(CapeanteInternacaoProjection.class);
		Root<CapeanteInternacao> root = query.from(getClazz());
		Join<CapeanteInternacao, ProntuarioCapeante> joinProntuario = root.join(CapeanteInternacao_.PRONTUARIO, JoinType.INNER);
		Join<ProntuarioCapeante, Paciente> joinPaciente = joinProntuario.join(ProntuarioCapeante_.PACIENTE, JoinType.INNER);
		Join<ProntuarioCapeante, HospitalCliente> joinHospitalCliente = joinProntuario.join(ProntuarioCapeante_.HOSPITAL, JoinType.INNER);
		Join<HospitalCliente, Cliente> joinCliente = joinHospitalCliente.join(HospitalCliente_.CLIENTE, JoinType.INNER);
		Join<HospitalCliente, Hospital> joinHospital = joinHospitalCliente.join(HospitalCliente_.HOSPITAL, JoinType.INNER);
		Join<CapeanteInternacao, StatusCapeante> joinStatus = root.join(CapeanteInternacao_.STATUS, JoinType.INNER);
		Join<CapeanteInternacao, Resumo> joinResumo = root.join(CapeanteInternacao_.RESUMO, JoinType.INNER);
		Join<CapeanteInternacao, Auditor> joinMedico = root.join(CapeanteInternacao_.MEDICO);
		Join<CapeanteInternacao, Auditor> joinEnfermeiro = root.join(CapeanteInternacao_.ENFERMEIRO);

		Path<Long> id = root.get(CapeanteInternacao_.ID);
		Path<LocalDate> inicioCobranca = root.get(CapeanteInternacao_.INICIO_COBRANCA);
		Path<Long> prontuario = joinProntuario.get(ProntuarioCapeante_.ID);
		Path<String> senhaInernacao = joinProntuario.get(ProntuarioCapeante_.SENHA_INTERNACAO);
		Path<String> observacao = joinProntuario.get(ProntuarioCapeante_.OBSERVACAO);
		Path<String> paciente = joinPaciente.get(Paciente_.NOME);
		Path<String> matricula = joinPaciente.get(Paciente_.MATRICULA);
		Path<String> hospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
		Path<String> cliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
		Path<String> status = joinStatus.get(StatusCapeante_.DESCRICAO);
		Expression<BigDecimal> valorApresentado = getValorApresentado(joinResumo);
		Expression<BigDecimal> valorGlosado = getValorGlosado(joinResumo);
		Expression<BigDecimal> valorLiberado = builder().diff(valorGlosado, joinResumo.get(Resumo_.DESCONTO));
		Path<String> medicoNome = joinMedico.get(Auditor_.NOME);
		Path<String> enfermeiroNome = joinEnfermeiro.get(Auditor_.NOME);
//		Path<Auditor> medico = root.get(CapeanteInternacao_.MEDICO);
//		Path<Auditor> enfermeiro = root.get(CapeanteInternacao_.ENFERMEIRO);
//		Path<String> observacao = root.get(CapeanteInternacao_.OBSERVACAO);

		query.multiselect(id, inicioCobranca, prontuario, senhaInernacao, paciente, matricula, hospital, cliente, status, valorApresentado, valorGlosado, valorLiberado, medicoNome, enfermeiroNome, observacao);
		Predicate predicate = toPredicate(params, root, joinProntuario, joinPaciente, joinHospitalCliente);
		query.where(predicate);
		query.orderBy(builder().asc(paciente));
		
		TypedQuery<CapeanteInternacaoProjection> typedQuery = super.entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
	
	@Override
	public Optional<CapeanteInternacao> detalharInternacao(Long id)
	{
		CriteriaQuery<CapeanteInternacao> criteria = super.getCriteria();
		Root<CapeanteInternacao> root = criteria.from(getClazz());
		Fetch<CapeanteInternacao, ProntuarioCapeante> fetchProntuario = root.fetch(CapeanteInternacao_.PRONTUARIO);
		Fetch<ProntuarioCapeante, HospitalCliente> fetchHospitalCliente = fetchProntuario.fetch(ProntuarioCapeante_.HOSPITAL);
		Fetch<ProntuarioCapeante, Paciente> fetchPaciente = fetchProntuario.fetch(ProntuarioCapeante_.PACIENTE);
		fetchPaciente.fetch(Paciente_.CLIENTE);
		fetchHospitalCliente.fetch(HospitalCliente_.HOSPITAL);
		root.fetch(CapeanteInternacao_.MEDICO, JoinType.LEFT);
		root.fetch(CapeanteInternacao_.ENFERMEIRO, JoinType.LEFT);
		root.fetch(CapeanteInternacao_.STATUS);
		root.fetch(CapeanteInternacao_.CID);
		root.fetch(CapeanteInternacao_.USUARIO);
		root.fetch(CapeanteInternacao_.PROCEDIMENTOS, JoinType.LEFT);
		root.fetch(CapeanteInternacao_.RESUMO);
		
		Predicate equalId = builder().equal(root.get(CapeanteInternacao_.ID), id);
		criteria.where(equalId);
		TypedQuery<CapeanteInternacao> typedQuery = super.entityManager.createQuery(criteria);
		
		try 
		{
			return Optional.of(typedQuery.getSingleResult());
		}catch (NoResultException e) 
		{
			return Optional.empty();
		}
	}

	private Expression<BigDecimal> getValorApresentado(Join<CapeanteInternacao, Resumo> joinResumo)
	{
		Path<BigDecimal> diariasValorApresentado = joinResumo.get(Resumo_.DIARIAS_VALOR_APRESENTADO);
		Path<BigDecimal> taxasValorApresentado = joinResumo.get(Resumo_.TAXAS_VALOR_APRESENTADO);
		Path<BigDecimal> gasesValorApresentado = joinResumo.get(Resumo_.GASES_VALOR_APRESENTADO);
		Path<BigDecimal> honorariosValorApresentado = joinResumo.get(Resumo_.HONORARIOS_VALOR_APRESENTADO);
		Path<BigDecimal> sadtValorApresentado = joinResumo.get(Resumo_.SADT_VALOR_APRESENTADO);
		Path<BigDecimal> materiaisValorApresentado = joinResumo.get(Resumo_.MATERIAIS_VALOR_APRESENTADO);
		Path<BigDecimal> matEspecialValorApresentado = joinResumo.get(Resumo_.MAT_ESPECIAL_VALOR_APRESENTADO);
		Path<BigDecimal> hemoderivadosValorApresentado = joinResumo.get(Resumo_.HEMODERIVADOS_VALOR_APRESENTADO);
		Path<BigDecimal> medicamentosValorApresentado = joinResumo.get(Resumo_.MEDICAMENTOS_VALOR_APRESENTADO);
		Path<BigDecimal> pacoteValorApresentado = joinResumo.get(Resumo_.PACOTE_VALOR_APRESENTADO);
		
		Expression<BigDecimal> valorApresentado = builder().sum(diariasValorApresentado, taxasValorApresentado);
		valorApresentado = builder().sum(valorApresentado, gasesValorApresentado);
		valorApresentado = builder().sum(valorApresentado, honorariosValorApresentado);
		valorApresentado = builder().sum(valorApresentado, sadtValorApresentado);
		valorApresentado = builder().sum(valorApresentado, materiaisValorApresentado);
		valorApresentado = builder().sum(valorApresentado, matEspecialValorApresentado);
		valorApresentado = builder().sum(valorApresentado, hemoderivadosValorApresentado);
		valorApresentado = builder().sum(valorApresentado, medicamentosValorApresentado);
		valorApresentado = builder().sum(valorApresentado, pacoteValorApresentado);
		return valorApresentado;
	}
	
	private Expression<BigDecimal> getValorGlosado(Join<CapeanteInternacao, Resumo> joinResumo)
	{
		Path<BigDecimal> diariasValorGlosado = joinResumo.get(Resumo_.DIARIAS_VALOR_GLOSADO);
		Path<BigDecimal> taxasValorGlosado = joinResumo.get(Resumo_.TAXAS_VALOR_GLOSADO);
		Path<BigDecimal> gasesValorGlosado = joinResumo.get(Resumo_.GASES_VALOR_GLOSADO);
		Path<BigDecimal> honorariosValorGlosado = joinResumo.get(Resumo_.HONORARIOS_VALOR_GLOSADO);
		Path<BigDecimal> sadtValorGlosado = joinResumo.get(Resumo_.SADT_VALOR_GLOSADO);
		Path<BigDecimal> materiaisValorGlosado = joinResumo.get(Resumo_.MATERIAIS_VALOR_GLOSADO);
		Path<BigDecimal> matEspecialValorGlosado = joinResumo.get(Resumo_.MAT_ESPECIAL_VALOR_GLOSADO);
		Path<BigDecimal> hemoderivadosValorGlosado = joinResumo.get(Resumo_.HEMODERIVADOS_VALOR_GLOSADO);
		Path<BigDecimal> medicamentosValorGlosado = joinResumo.get(Resumo_.MEDICAMENTOS_VALOR_GLOSADO);
		Path<BigDecimal> pacoteValorGlosado = joinResumo.get(Resumo_.PACOTE_VALOR_GLOSADO);
		
		Expression<BigDecimal> valorGlosado = builder().sum(diariasValorGlosado, taxasValorGlosado);
		valorGlosado = builder().sum(valorGlosado, gasesValorGlosado);
		valorGlosado = builder().sum(valorGlosado, honorariosValorGlosado);
		valorGlosado = builder().sum(valorGlosado, sadtValorGlosado);
		valorGlosado = builder().sum(valorGlosado, materiaisValorGlosado);
		valorGlosado = builder().sum(valorGlosado, matEspecialValorGlosado);
		valorGlosado = builder().sum(valorGlosado, hemoderivadosValorGlosado);
		valorGlosado = builder().sum(valorGlosado, medicamentosValorGlosado);
		valorGlosado = builder().sum(valorGlosado, pacoteValorGlosado);
		return valorGlosado;
	}

	private Predicate toPredicate(RestCapeanteInternacaoPesquisa params,
			Root<CapeanteInternacao> root,
			Join<CapeanteInternacao, ProntuarioCapeante> joinProntuario,
			Join<ProntuarioCapeante, Paciente> joinPaciente,
			Join<ProntuarioCapeante, HospitalCliente> joinHospitalCliente)
	{
		Predicate conjunction = builder().conjunction();
		 
		 if(params.getNumeroCapeante() != null)
		 {
			 Predicate equalCapeante = builder().equal(root.get(CapeanteInternacao_.PRONTUARIO).get(ProntuarioCapeante_.ID), params.getNumeroCapeante());
			 conjunction = builder().and(conjunction, equalCapeante);
		 }
		 
		 if(StringUtils.isNotBlank(params.getSenhaInternacao()))
		 {
			 Predicate likeSenha = builder().like(builder().upper(joinProntuario.get(ProntuarioCapeante_.SENHA_INTERNACAO)), like(params.getSenhaInternacao().toUpperCase()));
			 conjunction = builder().and(conjunction, likeSenha);
//			 Predicate equalSenha = builder().equal(joinProntuario.get(ProntuarioCapeante_.SENHA_INTERNACAO), params.getSenhaInternacao());
//			 conjunction = builder().and(conjunction, equalSenha);
		 }
		 
		 if(StringUtils.isNotBlank(params.getNumeroConta()))
		 {
			 Predicate equalContaHospitalar = builder().equal(joinProntuario.get(ProntuarioCapeante_.CONTA_HOSPITALAR), params.getNumeroConta());
			 conjunction = builder().and(conjunction, equalContaHospitalar);
		 }
		 
		 if(StringUtils.isNotBlank(params.getPaciente()))
		 {
			 Predicate equalPaciente = builder().equal(joinProntuario.get(ProntuarioCapeante_.PACIENTE).get(Paciente_.ID), UtilSecurity.decryptId(params.getPaciente()));
			 conjunction = builder().and(conjunction, equalPaciente);
		 }
		 
		 if(StringUtils.isNotBlank(params.getMatriculaPaciente()))
		 {
			 Predicate equalMatricula = builder().equal(joinPaciente.get(Paciente_.MATRICULA), params.getMatriculaPaciente());
			 conjunction = builder().and(conjunction, equalMatricula);
		 }
		 
		 if(StringUtils.isNotBlank(params.getCliente()))
		 {
			 Predicate equalCliente = builder().equal(joinPaciente.get(Paciente_.CLIENTE).get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
			 conjunction = builder().and(conjunction, equalCliente);
		 }

		 if(StringUtils.isNotBlank(params.getHospital()))
		 {
			 Predicate equalHospital = builder().equal(joinHospitalCliente.get(HospitalCliente_.HOSPITAL).get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
			 conjunction = builder().and(conjunction, equalHospital);
		 }
		 
		 if(params.getInicioCobranca() != null && params.getFimCobranca() != null)
		 {
			 Predicate between = builder().between(root.get(CapeanteInternacao_.INICIO_COBRANCA), params.getInicioCobranca(), params.getFimCobranca());
			 conjunction = builder().and(conjunction, between);
		 }
		 
		 if(params.getStatus() != null)
		 {
			 Predicate equalStatus = builder().equal(root.get(CapeanteInternacao_.STATUS).get(StatusCapeante_.ID), Long.parseLong(params.getStatus()));
			 conjunction = builder().and(conjunction, equalStatus);
		 }else {
			 Predicate notEqual = builder().notEqual(root.get(CapeanteInternacao_.STATUS).get(StatusCapeante_.ID), StatusCapeante.EXCLUIDO);
			 conjunction = builder().and(conjunction, notEqual);
		 }

		 if(params.getInternacao() != null){
			 Predicate equalInternacao = builder().equal(joinProntuario.get(ProntuarioCapeante_.ID), UtilSecurity.decryptId(params.getInternacao()));
			 conjunction = builder().and(conjunction, equalInternacao);
		 }
		 
		 return conjunction;
	}
}
