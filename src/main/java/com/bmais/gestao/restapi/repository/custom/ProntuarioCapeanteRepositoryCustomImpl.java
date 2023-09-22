package com.bmais.gestao.restapi.repository.custom;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.model.ProntuarioCapeante_;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Cliente_;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.HospitalCliente;
import com.bmais.gestao.restapi.model.HospitalCliente_;
import com.bmais.gestao.restapi.model.Hospital_;
import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.model.Paciente_;
import com.bmais.gestao.restapi.model.StatusCapeante;
import com.bmais.gestao.restapi.model.StatusCapeante_;
import com.bmais.gestao.restapi.model.enums.TipoAlta;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeantePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

public class ProntuarioCapeanteRepositoryCustomImpl extends Repository<ProntuarioCapeante> implements ProntuarioCapeanteRepositoryCustom {

    public Collection<ProntuarioCapeante> pesquisarCapeanteInternacao(RestProntuarioCapeantePesquisa params){
        CriteriaQuery<ProntuarioCapeante> criteria = super.getCriteria();
        Root<ProntuarioCapeante> root = criteria.from(getClazz());
        Join<ProntuarioCapeante, StatusCapeante> joinStatus = root.join(ProntuarioCapeante_.STATUS, JoinType.INNER);
    	Join<ProntuarioCapeante, Paciente> joinPaciente = root.join(ProntuarioCapeante_.PACIENTE, JoinType.INNER);
        Join<ProntuarioCapeante, HospitalCliente> joinHospitalCliente = root.join(ProntuarioCapeante_.HOSPITAL, JoinType.INNER);
        Join<HospitalCliente, Cliente> joinCliente = joinHospitalCliente.join(HospitalCliente_.CLIENTE, JoinType.INNER);
        Join<HospitalCliente, Hospital> joinHospital = joinHospitalCliente.join(HospitalCliente_.HOSPITAL, JoinType.INNER);
        Predicate conjunction = builder().conjunction();

        Path<Long> idCapeante = root.get(ProntuarioCapeante_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<String> nomeCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<String> nomeHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
        Path<LocalDate> dataInternacao = root.get(ProntuarioCapeante_.DATA_INTERNACAO);
        Path<LocalDate> dataAlta = root.get(ProntuarioCapeante_.DATA_ALTA);
        Path<TipoAlta> tipoAlta = root.get(ProntuarioCapeante_.TIPO_ALTA);
        Path<Boolean> obito = root.get(ProntuarioCapeante_.OBITO);
        Path<String> status = joinStatus.get(StatusCapeante_.DESCRICAO);

        criteria.multiselect(idCapeante, nomePaciente, nomeCliente, nomeHospital, dataInternacao, dataAlta, tipoAlta, obito, status);
        
        if(StringUtils.isNotBlank(params.getStatus()))
        {
            Predicate equal = builder().equal(root.get(ProntuarioCapeante_.STATUS).get(StatusCapeante_.ID), Long.parseLong(params.getStatus()));
            conjunction = builder().and(conjunction, equal);
        }else {
            Predicate notEqual = builder().notEqual(root.get(ProntuarioCapeante_.STATUS).get(StatusCapeante_.ID), StatusCapeante.EXCLUIDO);
            conjunction = builder().and(conjunction, notEqual);
        }
        
        if(StringUtils.isNotBlank(params.getHospital()))
        {

            Predicate equal = builder().equal(joinHospitalCliente.get(HospitalCliente_.HOSPITAL).get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
            conjunction = builder().and(conjunction, equal);
        }
        
        if(StringUtils.isNotBlank(params.getCliente()))
        {
              Predicate equal = builder().equal(joinHospitalCliente.get(HospitalCliente_.CLIENTE).get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
              conjunction = builder().and(conjunction, equal);
        }
        
        if(StringUtils.isNotBlank(params.getPaciente()))
        {

        	Predicate equal = builder().equal(joinPaciente.get(Paciente_.ID), UtilSecurity.decryptId(params.getPaciente()));
        	conjunction = builder().and(conjunction, equal);
        }
        
        if(params.getDataInicioInternacao() != null && params.getDataFimInternacao() != null)
        {
        	Predicate between = builder().between(root.get(ProntuarioCapeante_.DATA_INTERNACAO), params.getDataInicioInternacao(), params.getDataFimInternacao());
        	conjunction = builder().and(conjunction, between);
        }
        
        if(params.getDataInicioAlta() != null && params.getDataFimAlta() != null)
        {
        	Predicate between = builder().between(root.get(ProntuarioCapeante_.DATA_ALTA), params.getDataInicioAlta(), params.getDataFimAlta());
        	conjunction = builder().and(conjunction, between);
        }
        
        criteria.where(conjunction);
        criteria.orderBy(builder().asc(nomePaciente));
        TypedQuery<ProntuarioCapeante> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    public Long numeroCapeante(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_numero_capeante')")
                .getSingleResult().toString());
    }


    @Override
    public Class<ProntuarioCapeante> getClazz(){return ProntuarioCapeante.class;}

	@Override
	public Optional<ProntuarioCapeante> detalharCapeanteInternacao(Long id)
	{
		 CriteriaQuery<ProntuarioCapeante> criteria = super.getCriteria();
         Root<ProntuarioCapeante> root = criteria.from(getClazz());
         root.fetch(ProntuarioCapeante_.STATUS, JoinType.INNER);
    	 Fetch<ProntuarioCapeante, Paciente> fetchPaciente = root.fetch(ProntuarioCapeante_.PACIENTE, JoinType.INNER);
         Fetch<ProntuarioCapeante_, HospitalCliente> fetchHospitalcliente = root.fetch(ProntuarioCapeante_.HOSPITAL, JoinType.INNER);
         fetchPaciente.fetch(HospitalCliente_.CLIENTE, JoinType.INNER);
         fetchHospitalcliente.fetch(HospitalCliente_.HOSPITAL, JoinType.INNER);
         
         criteria.where(builder().equal(root.get(ProntuarioCapeante_.ID), id));
         
         try
         {
        	 TypedQuery<ProntuarioCapeante> typedQuery = entityManager.createQuery(criteria);
        	 return Optional.of(typedQuery.getSingleResult());
         }catch (NoResultException e) 
         {
			return Optional.empty();
		 }
	}
}
