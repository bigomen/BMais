package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.repository.HospitalRepository;
import com.bmais.gestao.restapi.repository.PacienteRepository;
import com.bmais.gestao.restapi.restmodel.RestInternacaoPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class InternacaoRepositoryCustomImpl  extends Repository<Internacao> implements InternacaoRepositoryCustom {

    private final PacienteRepository pacienteRepository;

    private final HospitalRepository hospitalRepository;

    public InternacaoRepositoryCustomImpl(PacienteRepository pacienteRepository, HospitalRepository hospitalRepository) {
        this.pacienteRepository = pacienteRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Collection<Internacao> lista(RestInternacaoPesquisa params){

        CriteriaQuery<Internacao> criteria = super.getCriteria();
        Root<Internacao> root = criteria.from(getClazz());
        final Predicate[] conjunction = {builder().conjunction()};

        Join<Internacao, Paciente> joinPaciente = root.join(Internacao_.PACIENTE);
        Join<Paciente, Cliente> joinCliente = joinPaciente.join(Paciente_.CLIENTE);
        Join<Internacao, StatusInternacao> joinStatus = root.join(Internacao_.STATUS, JoinType.INNER);
        Join<Internacao, Hospital> joinHospital = root.join(Internacao_.HOSPITAL);
        
        Path<Long> idInternacao = root.get(Internacao_.ID);
        Path<Long> idPaciente = joinPaciente.get(Paciente_.ID);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<Date> nascimentoPaciente = joinPaciente.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matriculaPaciente = joinPaciente.get(Paciente_.MATRICULA);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<Date> dataInternacao = root.get(Internacao_.DATA_HORA);
        Path<StatusInternacao> status = root.get(Internacao_.STATUS);
        Path<Long> idHospital = joinHospital.get(Hospital_.ID);
        Path<String> razaoSocialHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
        Path<Sexo> sexoPaciente = joinPaciente.get(Paciente_.SEXO);
        Path<LocalDateTime> dataHoraAlta = root.get(Internacao_.DATA_HORA_ALTA);
        Path<String> senhaConvenio = root.get(Internacao_.SENHA);
        Path<Integer> diasLiberados = root.get(Internacao_.DIAS_LIBERADOS);
        Path<Boolean> obito = root.get(Internacao_.OBITO);

        criteria.multiselect(idInternacao, idPaciente, idCliente, idHospital, nomePaciente, nascimentoPaciente,
                matriculaPaciente, razaoCliente, razaoSocialHospital, dataInternacao, status, sexoPaciente, dataHoraAlta, senhaConvenio, diasLiberados, obito);

        if(StringUtils.isNotBlank(params.getPaciente()))
        {
        	Predicate equalPaciente = builder().equal(idPaciente, UtilSecurity.decryptId(params.getPaciente()));
        	conjunction[0] = builder().and(conjunction[0], equalPaciente);
        }
        
        if(StringUtils.isNotBlank(params.getNomePaciente())){
            Predicate equal = builder().equal(joinPaciente.get(Paciente_.ID), UtilSecurity.decryptId(params.getNomePaciente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(params.getDataInicioInternacao() != null && params.getDataFinalInternacao() != null){
            Predicate betweenData = builder().between(root.get(Internacao_.DATA_HORA), params.getDataInicioInternacao().atStartOfDay(), params.getDataFinalInternacao().atTime(23, 59, 59));
            conjunction[0] = builder().and(conjunction[0], betweenData);
        }
        if(params.getStatus() != null){
            Predicate equal = builder().equal(joinStatus.get(StatusInternacao_.ID), params.getStatus());
            conjunction[0] = builder().and(conjunction[0], equal);
        }else {
            Predicate notEqual = builder().notEqual(joinStatus.get(StatusInternacao_.ID), StatusInternacao.EXCLUIDO);
            conjunction[0] = builder().and(conjunction[0], notEqual);
        }
        if(params.getVinculos() != null){
            params.getVinculos().forEach(v -> {
                Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), v.getHospitalId());
                conjunction[0] = builder().and(conjunction[0], equal);
                equal = builder().equal(joinCliente.get(Cliente_.ID), v.getClienteId());
                conjunction[0] = builder().and(conjunction[0], equal);
            });
        }

        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(nomePaciente));
        
        TypedQuery<Internacao> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Internacao detalhes(Long id){
        CriteriaQuery<Internacao> criteria = super.getCriteria();
        Root<Internacao> root = criteria.from(getClazz());

        criteria.where(builder().equal(root.get(Internacao_.ID), id));
        TypedQuery<Internacao> typedQuery = entityManager.createQuery(criteria);
        Internacao internacao = typedQuery.getSingleResult();
        internacao.setPaciente(pacienteRepository.findById(internacao.getPaciente().getId()).get());
        internacao.setHospital(hospitalRepository.internacaoHospital(internacao.getHospital().getId()));
        return internacao;
    }

    @Override
    public Long numeroInternacao(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_numero_internacao')")
                .getSingleResult().toString());
    }


    @Override
    public List<Internacao> pesquisar(Long paciente, Long cliente) {
        CriteriaQuery<Internacao> criteria = super.getCriteria();
        Root<Internacao> root = criteria.from(getClazz());

        Predicate conjunction = builder().conjunction();

        Join<Internacao, Paciente> joinPaciente = root.join(Internacao_.PACIENTE);
        Join<Paciente, Cliente> joinCliente = joinPaciente.join(Paciente_.CLIENTE);
        Path<Long> idInternacao = root.get(Internacao_.ID);
        Path<LocalDateTime> dataHoraInternacao = root.get(Internacao_.DATA_HORA);
        Path<LocalDateTime> dataHoraAltaInternacao = root.get(Internacao_.DATA_HORA_ALTA);
        Path<Long> idPaciente = joinPaciente.get(Paciente_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<LocalDate> dataNascimentoPaciente = joinPaciente.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matriculaPaciente = joinPaciente.get(Paciente_.MATRICULA);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoSocialCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);

        criteria.multiselect(idInternacao, dataHoraInternacao, dataHoraAltaInternacao, idPaciente, nomePaciente,
                             dataNascimentoPaciente, matriculaPaciente, idCliente, razaoSocialCliente);
        if (paciente != null) {
            conjunction = builder().and(conjunction, builder().equal(idPaciente, paciente));
        }

        if (cliente != null) {
            conjunction = builder().and(conjunction, builder().equal(idCliente, cliente));
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(nomePaciente));
        TypedQuery<Internacao> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Internacao> getClazz() {
        return Internacao.class;
    }
}
