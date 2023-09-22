package com.bmais.gestao.restapi.repository.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.bmais.gestao.restapi.model.*;
import org.apache.commons.lang3.StringUtils;

import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.restmodel.RestPacientePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@org.springframework.stereotype.Repository
public class PacienteRepositoryCustomImpl extends Repository<Paciente> implements PacienteRepositoryCustom {

    @Override
    public Collection<Paciente> lista(RestPacientePesquisa params){
        CriteriaQuery<Paciente> criteria = super.getCriteria();
        Root<Paciente> root = criteria.from(getClazz());
        final Predicate[] conjunction = {builder().conjunction()};

        Join<Paciente, Cliente> joinCliente = root.join(Paciente_.CLIENTE);
        Join<Paciente, StatusPaciente> joinStatus = root.join(Paciente_.STATUS);

        Path<Long> idPaciente = root.get(Paciente_.ID);
        Path<String> nomePaciente = root.get(Paciente_.NOME);
        Path<Date> dataNascimento = root.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matricula = root.get(Paciente_.MATRICULA);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> nomeCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<StatusPaciente> status = root.get(Paciente_.STATUS);
        Path<Sexo> sexo = root.get(Paciente_.SEXO);

        criteria.multiselect(idPaciente, nomePaciente, dataNascimento, matricula, idCliente, nomeCliente, status, sexo);

        if(StringUtils.isNotBlank(params.getPaciente())){
            Predicate equal = builder().like(builder().upper(root.get(Paciente_.NOME)), like(params.getPaciente().toUpperCase()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(StringUtils.isNotBlank(params.getMatricula())){
            Predicate like = builder().like(builder().upper(root.get(Paciente_.MATRICULA)), like(params.getMatricula().toUpperCase()));
            conjunction[0] = builder().and(conjunction[0], like);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Predicate equal = builder().equal(root.get(Paciente_.STATUS).get(StatusPaciente_.ID), Long.parseLong(params.getStatus()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(params.getVinculos() != null){
            params.getVinculos().forEach(v -> {
                Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), v.getClienteId());
                conjunction[0] = builder().and(conjunction[0], equal);
            });
        }
        Predicate excluidos = builder().notEqual(joinStatus.get(StatusPaciente_.ID), StatusPaciente.EXCLUIDO);
        conjunction[0] = builder().and(conjunction[0], excluidos);
        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(nomePaciente));
        TypedQuery<Paciente> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Paciente> listaInternados(RestPacientePesquisa params){
        CriteriaQuery<Paciente> criteria = super.getCriteria();
        Root<Paciente> root = criteria.from(getClazz());
        final Predicate[] conjunction = {builder().conjunction()};

        Join<Paciente, Cliente> joinCliente = root.join(Paciente_.CLIENTE);
        Join<Paciente, List<Internacao>> joinInternacao = root.join(Paciente_.INTERNACOES);

        Path<Long> idPaciente = root.get(Paciente_.ID);
        Path<String> nomePaciente = root.get(Paciente_.NOME);
        Path<Date> dataNascimento = root.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matricula = root.get(Paciente_.MATRICULA);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> nomeCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<StatusPaciente> status = root.get(Paciente_.STATUS);
        //Path<List<Long>> idInternacoes = joinInternacao.get(Internacao_.ID);

        criteria.multiselect(idPaciente, nomePaciente, dataNascimento, matricula, idCliente, nomeCliente, status);

        Predicate filterInternacao = builder().equal(joinInternacao.get(Internacao_.STATUS), StatusInternacao.ATIVO);
        conjunction[0] = builder().and(conjunction[0], filterInternacao);
        
        if(StringUtils.isNotBlank(params.getPaciente())){
            Predicate equal = builder().like(builder().upper(root.get(Paciente_.NOME)), like(params.getPaciente().toUpperCase()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(StringUtils.isNotBlank(params.getMatricula())){
            Predicate like = builder().like(builder().upper(root.get(Paciente_.MATRICULA)), like(params.getMatricula().toUpperCase()));
            conjunction[0] = builder().and(conjunction[0], like);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Join<Paciente, StatusPaciente> joinStatus = root.join(Paciente_.STATUS, JoinType.INNER);
            Predicate equal = builder().equal(joinStatus.get(StatusVisita_.ID), UtilSecurity.decryptId(params.getStatus()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(params.getVinculos() != null){
            params.getVinculos().forEach(v -> {
                Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), v.getClienteId());
                conjunction[0] = builder().and(conjunction[0], equal);
            });
        }
        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(nomePaciente));
        TypedQuery<Paciente> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }
    
    @Override
    public Paciente detalhes(Long id){
        CriteriaQuery<Paciente> criteria = super.getCriteria();
        Root<Paciente> root = criteria.from(getClazz());
        Join<Paciente, Cliente> joinCliente = root.join(Paciente_.CLIENTE);
        Join<Paciente, StatusPaciente> joinStatus = root.join(Paciente_.STATUS, JoinType.INNER);

        Path<Long> idPaciente = root.get(Paciente_.ID);
        Path<String> nomePaciente = root.get(Paciente_.NOME);
        Path<Date> dataNascimento = root.get(Paciente_.DATA_NASCIMENTO);
        Path<Sexo> sexo = root.get(Paciente_.SEXO);
        Path<String> matricula = root.get(Paciente_.MATRICULA);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<String> observacao = root.get(Paciente_.OBSERVACAO);
        Path<Long> idStatus = joinStatus.get(StatusPaciente_.ID);
        Path<String> descStatus = joinStatus.get(StatusPaciente_.DESCRICAO);

        criteria.where(builder().equal(root.get(Paciente_.ID), id));

        criteria.multiselect(idPaciente, nomePaciente, dataNascimento, sexo, matricula, idCliente, razaoCliente, observacao, idStatus, descStatus);

        TypedQuery<Paciente> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getSingleResult();
    }

    public Collection<Paciente> listaPacientesVisitaConcorrente(Collection<Long> hospitais){
        CriteriaQuery<Paciente> criteria = builder().createQuery(Paciente.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Root<Internacao> root = criteria.from(Internacao.class);
        List<Predicate> conjunction = new ArrayList<>();

        Join<Internacao, Paciente> joinPaciente = root.join(Internacao_.PACIENTE);
        Join<Internacao, Hospital> joinHospital = root.join(Internacao_.HOSPITAL);

        Path<Long> pacienteId = joinPaciente.get(Paciente_.ID);
        Path<String> pacienteNome = joinPaciente.get(Paciente_.NOME);

        criteria.multiselect(pacienteId, pacienteNome);

        hospitais.forEach(h -> {
            Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), h);
            conjunction.add(equal);
        });
        criteria.where(builder().or(conjunction.toArray(new Predicate[0])));
        criteria.orderBy(cb.asc(pacienteNome));
        criteria.distinct(true);
        TypedQuery<Paciente> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Paciente> getClazz() {
        return Paciente.class;
    }
}
