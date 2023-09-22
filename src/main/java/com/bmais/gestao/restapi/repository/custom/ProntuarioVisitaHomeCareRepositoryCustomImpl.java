package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.PacienteRepository;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCarePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProntuarioVisitaHomeCareRepositoryCustomImpl  extends Repository<ProntuarioVisitaHomeCare> implements ProntuarioVisitaHomeCareRepositoryCustom {

    private final PacienteRepository pacienteRepository;


    public ProntuarioVisitaHomeCareRepositoryCustomImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Collection<ProntuarioVisitaHomeCare> lista(RestProntuarioVisitaHomeCarePesquisa params){

        CriteriaQuery<ProntuarioVisitaHomeCare> criteria = super.getCriteria();
        Root<ProntuarioVisitaHomeCare> root = criteria.from(getClazz());
        final Predicate[] conjunction = {builder().conjunction()};

        Join<ProntuarioVisitaHomeCare, Paciente> joinPaciente = root.join(ProntuarioVisitaHomeCare_.PACIENTE);
        Join<Paciente, Cliente> joinCliente = joinPaciente.join(Paciente_.CLIENTE);
        Join<ProntuarioVisitaHomeCare, StatusProntuarioVisitaHomeCare> joinStatus = root.join(ProntuarioVisitaHomeCare_.STATUS, JoinType.INNER);
        Join<ProntuarioVisitaHomeCare, ProntuarioMotivoAlta> joinMotivoAlta = root.join(ProntuarioVisitaHomeCare_.MOTIVO_ALTA, JoinType.LEFT);
        
        Path<Long> idProntuarioVisitaHomeCare = root.get(ProntuarioVisitaHomeCare_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<String> empresa = root.get(ProntuarioVisitaHomeCare_.EMPRESA);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<String> hospital = root.get(ProntuarioVisitaHomeCare_.HOSPITAL);
        Path<Date> dataInicio = root.get(ProntuarioVisitaHomeCare_.INICIO);
        Path<LocalDate> dataHoraAlta = root.get(ProntuarioVisitaHomeCare_.ALTA);
        Path<String> motivoAlta = joinMotivoAlta.get(ProntuarioMotivoAlta_.DESCRICAO);
        Path<Boolean> obito = root.get(ProntuarioVisitaHomeCare_.OBITO);
        Path<String> status = joinStatus.get(StatusProntuarioVisitaHomeCare_.DESCRICAO);

        criteria.multiselect(idProntuarioVisitaHomeCare, nomePaciente, empresa, razaoCliente, hospital, dataInicio, dataHoraAlta, motivoAlta, obito, status);

        if(StringUtils.isNotBlank(params.getPaciente())){
            Predicate equal = builder().equal(joinPaciente.get(Paciente_.ID), UtilSecurity.decryptId(params.getPaciente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        
        if(StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        
        if(params.getInicio() != null && params.getFim() != null){
            Predicate betweenData = builder().between(root.get(ProntuarioVisitaHomeCare_.INICIO), params.getInicio(), params.getFim());
            conjunction[0] = builder().and(conjunction[0], betweenData);
        }
        
        if(params.getAltaInicio() != null && params.getAltaFim() != null){
        	Predicate betweenData = builder().between(dataHoraAlta, params.getAltaInicio(), params.getAltaFim());
        	conjunction[0] = builder().and(conjunction[0], betweenData);
        }
        
        if(StringUtils.isNotBlank(params.getStatus())){
            Predicate equal = builder().equal(joinStatus.get(StatusProntuarioVisitaHomeCare_.ID), Long.parseLong(params.getStatus()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }else {
            Predicate notEqual = builder().notEqual(joinStatus.get(StatusProntuarioVisitaHomeCare_.ID), StatusProntuarioVisitaHomeCare.EXCLUIDO);
            conjunction[0] = builder().and(conjunction[0], notEqual);
        }
        
        if(StringUtils.isNotBlank(params.getEmpresa()))
        {
        	Predicate like = builder().like(builder().upper(empresa), like(params.getEmpresa().toUpperCase()));
        	conjunction[0] = builder().and(conjunction[0], like);
        }
        
        if(StringUtils.isNotBlank(params.getHospital()))
        {
              Predicate like = builder().like(builder().upper(hospital), like(params.getHospital().toUpperCase()));
              conjunction[0] = builder().and(conjunction[0], like);
        }
        
//        if(params.getVinculos() != null){
//            params.getVinculos().forEach(v -> {
//                Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), v.getClienteId());
//            });
//        }
        
        
        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(nomePaciente));
        
        TypedQuery<ProntuarioVisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public ProntuarioVisitaHomeCare detalhes(Long id){
        CriteriaQuery<ProntuarioVisitaHomeCare> criteria = super.getCriteria();
        Root<ProntuarioVisitaHomeCare> root = criteria.from(getClazz());

        criteria.where(builder().equal(root.get(ProntuarioVisitaHomeCare_.ID), id));
        TypedQuery<ProntuarioVisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
        ProntuarioVisitaHomeCare prontuario = typedQuery.getSingleResult();
        prontuario.setPaciente(pacienteRepository.findById(prontuario.getPaciente().getId()).get());
        return prontuario;
    }

    @Override
    public Long numeroProntuario(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_numero_prontuario')")
                .getSingleResult().toString());
    }


    @Override
    public List<ProntuarioVisitaHomeCare> pesquisar(Long paciente, Long cliente) {
        CriteriaQuery<ProntuarioVisitaHomeCare> criteria = super.getCriteria();
        Root<ProntuarioVisitaHomeCare> root = criteria.from(getClazz());

        Predicate conjunction = builder().conjunction();

        Join<ProntuarioVisitaHomeCare, Paciente> joinPaciente = root.join(ProntuarioVisitaHomeCare_.PACIENTE);
        Join<Paciente, Cliente> joinCliente = joinPaciente.join(Paciente_.CLIENTE);
        Path<Long> idProntuarioVisitaHomeCare = root.get(ProntuarioVisitaHomeCare_.ID);
        Path<LocalDateTime> dataHoraProntuarioVisitaHomeCare = root.get(ProntuarioVisitaHomeCare_.INICIO);
        Path<LocalDateTime> dataHoraAltaProntuarioVisitaHomeCare = root.get(ProntuarioVisitaHomeCare_.ALTA);
        Path<Long> idPaciente = joinPaciente.get(Paciente_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<LocalDate> dataNascimentoPaciente = joinPaciente.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matriculaPaciente = joinPaciente.get(Paciente_.MATRICULA);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoSocialCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);

        criteria.multiselect(idProntuarioVisitaHomeCare, dataHoraProntuarioVisitaHomeCare, dataHoraAltaProntuarioVisitaHomeCare, idPaciente, nomePaciente,
                             dataNascimentoPaciente, matriculaPaciente, idCliente, razaoSocialCliente);
        if (paciente != null) {
            conjunction = builder().and(conjunction, builder().equal(idPaciente, paciente));
        }

        if (cliente != null) {
            conjunction = builder().and(conjunction, builder().equal(idCliente, cliente));
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(nomePaciente));
        TypedQuery<ProntuarioVisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<ProntuarioVisitaHomeCare> getClazz() {
        return ProntuarioVisitaHomeCare.class;
    }

	@Override
	public Optional<ProntuarioVisitaHomeCare> pesquisarUltimoProntuarioPaciente(
			Paciente paciente)
	{
		CriteriaQuery<ProntuarioVisitaHomeCare> criteria = super.getCriteria();
	    Root<ProntuarioVisitaHomeCare> root = criteria.from(getClazz());
	    
	    Subquery<Long> sub = criteria.subquery(Long.class);
	    Root<ProntuarioVisitaHomeCare> rootSub = sub.from(getClazz());
	    Expression<Long> ultimoProntuario = builder().max(rootSub.get(ProntuarioVisitaHomeCare_.ID));
	    Predicate equalPaciente = builder().equal(rootSub.get(ProntuarioVisitaHomeCare_.PACIENTE), paciente);
	    sub.where(equalPaciente);
	    sub.select(ultimoProntuario);
	    
	    Predicate equalSub = builder().equal(root.get(ProntuarioVisitaHomeCare_.ID), sub);
	    criteria.where(equalSub);
	    
	    TypedQuery<ProntuarioVisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
	    
	    try
	    {
	    	return Optional.of(typedQuery.getSingleResult());
	    }catch (NoResultException e) {
			return Optional.empty();
		}
	}
}
