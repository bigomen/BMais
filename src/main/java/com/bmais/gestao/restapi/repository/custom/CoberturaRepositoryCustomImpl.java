package com.bmais.gestao.restapi.repository.custom;


import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.restmodel.RestCoberturaPesquisa;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@org.springframework.stereotype.Repository
public class CoberturaRepositoryCustomImpl extends Repository<Cobertura> implements CoberturaRepositoryCustom{

    @Override
    public Collection<Cobertura> lista(RestCoberturaPesquisa params) {
        CriteriaQuery<Cobertura> criteria = super.getCriteria();
        Root<Cobertura> root = criteria.from(getClazz());
//        Predicate conjunction = builder().conjunction();
//
//        Join<Cobertura, Auditor> joinAuditorCobertura = root.join(Cobertura_.AUDITOR_COBERTURA, JoinType.LEFT);
//        Join<Cobertura, Auditor> joinAuditorSubstituido = root.join(Cobertura_.AUDITOR_SUBSTITUIDO, JoinType.LEFT);
////        Join<Cobertura, Hospital> joinHospital = root.join(Cobertura_.HOSPITAL);
////        Join<Cobertura, Cliente> joinCliente = root.join(Cobertura_.CLIENTE);
////        Join<Cobertura, Servico> joinServico = root.join(Cobertura_.SERVICO);
//
//        Path<Long> idCobertura = root.get(Cobertura_.ID);
//        Path<Long> idAuditorCobertura = joinAuditorCobertura.get(Auditor_.ID);
//        Path<Long> idAuditorSubstituido = joinAuditorSubstituido.get(Auditor_.ID);
//        Path<Long> idHospital = joinHospital.get(Hospital_.ID);
//        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
//        Path<Long> idServico = joinServico.get(Servico_.ID);
//
//        Path<String> auditorCobertura = joinAuditorCobertura.get(Auditor_.NOME);
//        Path<String> auditorSubstituido = joinAuditorSubstituido.get(Auditor_.NOME);
//        Path<Date> periodoInicio = root.get(Cobertura_.PERIODO_INICIO);
//        Path<Date> periodoFinal = root.get(Cobertura_.PERIODO_FIM);
//        Path<String> razaoHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
//        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
//        Path<String> descricaoServico = joinServico.get(Servico_.DESCRICAO);
//        Path<String> observacao = root.get(Cobertura_.OBSERVACAO);
//
//        criteria.multiselect(idCobertura, idAuditorCobertura, auditorCobertura, idAuditorSubstituido, auditorSubstituido, idHospital, razaoHospital,
//                idCliente, razaoCliente, idServico, descricaoServico, periodoInicio, periodoFinal, observacao);
//
//        if(StringUtils.isNotBlank(params.getTipoAuditor())){
//            Join<Auditor, TipoAuditor> joinTipoAuditor = joinAuditorCobertura.join(Auditor_.TIPO_AUDITOR);
//            if (Objects.equals(params.getTipoAuditor(), "M")){
//                Predicate equal = builder().equal(joinTipoAuditor.get(TipoAuditor_.ID), 1);
//                conjunction = builder().and(conjunction, equal);
//            }
//            if (Objects.equals(params.getTipoAuditor(), "E")){
//                Predicate equal = builder().equal(joinTipoAuditor.get(TipoAuditor_.ID), 2);
//                conjunction = builder().and(conjunction, equal);
//            }
//        }
//        if(StringUtils.isNotBlank(params.getAuditor())){
//            Predicate equal = builder().equal(joinAuditorCobertura.get(Auditor_.ID), UtilSecurity.decryptId(params.getAuditor()));
//            conjunction = builder().and(conjunction, equal);
//        }
//        if(StringUtils.isNotBlank(params.getHospital())){
//            Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
//            conjunction = builder().and(conjunction, equal);
//        }
//        if(params.getPeriodoInicial() != null && params.getPeriodoFinal() != null){
//            Predicate between = builder().between(root.get(Cobertura_.PERIODO_INICIO), params.getPeriodoInicial(), params.getPeriodoFinal());
//            conjunction = builder().and(conjunction, between);
//        }
//        if(StringUtils.isNotBlank(params.getCliente())){
//            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
//            conjunction = builder().and(conjunction, equal);
//        }
//        if(StringUtils.isNotBlank(params.getServico())){
//            Predicate equal = builder().equal(joinServico.get(Servico_.ID), UtilSecurity.decryptId(params.getServico()));
//            conjunction = builder().and(conjunction, equal);
//        }
//
//        criteria.where(conjunction);
        TypedQuery<Cobertura> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Cobertura> listaCobertura(Long id) {
        CriteriaQuery<Cobertura> criteria = super.getCriteria();
        Root<Cobertura> root = criteria.from(getClazz());
//        Predicate conjunction = builder().conjunction();
//
//        Join<Cobertura, Auditor> joinAuditorSubstituido = root.join(Cobertura_.AUDITOR_SUBSTITUIDO);
//        Join<Cobertura, Auditor> joinAuditorCobertura = root.join(Cobertura_.AUDITOR_COBERTURA, JoinType.LEFT);
//        Join<Cobertura, Hospital> joinHospital = root.join(Cobertura_.HOSPITAL);
//        Join<Cobertura, Cliente> joinCliente = root.join(Cobertura_.CLIENTE);
//        Join<Cobertura, Servico> joinServico = root.join(Cobertura_.SERVICO);
//
//        Predicate equal = builder().equal(joinAuditorSubstituido.get(Auditor_.ID), id);
//        conjunction = builder().and(conjunction, equal);
//
//        Path<Long> idCobertura = root.get(Cobertura_.ID);
//        Path<Long> idAuditorCobertura = joinAuditorCobertura.get(Auditor_.ID);
//        Path<Long> idAuditorSubstituido = joinAuditorSubstituido.get(Auditor_.ID);
//        Path<Long> idHospital = joinHospital.get(Hospital_.ID);
//        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
//        Path<Long> idServico = joinServico.get(Servico_.ID);
//
//        Path<String> auditorCobertura = joinAuditorCobertura.get(Auditor_.NOME);
//        Path<String> auditorSubstituido = joinAuditorSubstituido.get(Auditor_.NOME);
//        Path<Date> periodoInicio = root.get(Cobertura_.PERIODO_INICIO);
//        Path<Date> periodoFinal = root.get(Cobertura_.PERIODO_FIM);
//        Path<String> razaoHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
//        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
//        Path<String> descricaoServico = joinServico.get(Servico_.DESCRICAO);
//        Path<String> observacao = root.get(Cobertura_.OBSERVACAO);
//
//        criteria.multiselect(idCobertura, idAuditorCobertura, auditorCobertura, idAuditorSubstituido, auditorSubstituido, idHospital, razaoHospital,
//                idCliente, razaoCliente, idServico, descricaoServico, periodoInicio, periodoFinal, observacao);
//
//        criteria.where(conjunction);
        TypedQuery<Cobertura> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Cobertura> listaEscala(RestEscalaPesquisa params){
        CriteriaQuery<Cobertura> criteria = super.getCriteria();
        Root<Cobertura> root = criteria.from(getClazz());
//        final Predicate[] conjunction = {builder().conjunction()};
//
//        Join<Cobertura, Auditor> joinAuditorCobertura = root.join(Cobertura_.AUDITOR_COBERTURA, JoinType.LEFT);
//        Join<Cobertura, Auditor> joinAuditorSubstituido = root.join(Cobertura_.AUDITOR_SUBSTITUIDO, JoinType.LEFT);
//        Join<Cobertura, Hospital> joinHospital = root.join(Cobertura_.HOSPITAL);
//        Join<Cobertura, Cliente> joinCliente = root.join(Cobertura_.CLIENTE);
//        Join<Cobertura, Servico> joinServico = root.join(Cobertura_.SERVICO);
//        Join<Hospital, Endereco> joinEndereco = joinHospital.join(Hospital_.ENDERECO);
//        Join<Auditor, Usuario> joinUsuario = joinAuditorSubstituido.join(Auditor_.USUARIO);
//
//        Path<Long> idCobertura = root.get(Cobertura_.ID);
//        Path<Long> idAuditorCobertura = joinAuditorCobertura.get(Auditor_.ID);
//        Path<Long> idAuditorSubstituido = joinAuditorSubstituido.get(Auditor_.ID);
//        Path<Long> idHospital = joinHospital.get(Hospital_.ID);
//        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
//        Path<Long> idServico = joinServico.get(Servico_.ID);
//        Path<Long> idUsuario = joinUsuario.get(Usuario_.ID);
//        Path<Long> idEndereco = joinEndereco.get(Endereco_.ID);
//
//        Path<String> auditorCobertura = joinAuditorCobertura.get(Auditor_.NOME);
//        Path<String> auditorSubstituido = joinAuditorSubstituido.get(Auditor_.NOME);
//        Path<Date> periodoInicio = root.get(Cobertura_.PERIODO_INICIO);
//        Path<Date> periodoFinal = root.get(Cobertura_.PERIODO_FIM);
//        Path<String> razaoHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
//        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
//        Path<String> descricaoServico = joinServico.get(Servico_.DESCRICAO);
//        Path<String> corem = joinAuditorSubstituido.get(Auditor_.CRM_COREM);
//        Path<TipoAuditor> tipoAuditor = joinAuditorSubstituido.get(Auditor_.TIPO_AUDITOR);
//        Path<StatusAuditor> statusAuditor = joinAuditorSubstituido.get(Auditor_.STATUS);
//        Path<String> emailAuditor = joinAuditorSubstituido.get(Auditor_.EMAIL);
//        Path<String> emailUsuario = joinUsuario.get(Usuario_.EMAIL);
//        Path<Municipio> municipioHospital = joinEndereco.get(Endereco_.MUNICIPIO);
//
//        criteria.multiselect(idCobertura, idAuditorCobertura, auditorCobertura, idAuditorSubstituido, auditorSubstituido, idHospital, razaoHospital,
//                idCliente, razaoCliente, idServico, descricaoServico, periodoInicio, periodoFinal, corem, tipoAuditor, statusAuditor, emailAuditor,
//                idUsuario, emailUsuario, municipioHospital, idEndereco);
//
//        if(StringUtils.isNotBlank(params.getAuditor())){
//            String[] divColab = params.getAuditor().split("_");
//            Collection<String> auditores = new ArrayList<>(List.of(divColab));
//            auditores.forEach(a ->{
//                Predicate equal = builder().equal(joinAuditorSubstituido.get(Auditor_.ID), UtilSecurity.decryptId(a));
//                conjunction[0] = builder().or(conjunction[0], equal);
//            });
//        }
//        if(StringUtils.isNotBlank(params.getCliente())){
//            String[] divColab = params.getCliente().split("_");
//            Collection<String> clientes = new ArrayList<>(List.of(divColab));
//            clientes.forEach(c -> {
//                Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(c));
//                conjunction[0] = builder().or(conjunction[0], equal);
//            });
//        }
//        if(params.getHospital() != null){
//            String[] divColab = params.getHospital().split("_");
//            Collection<String> hospitais = new ArrayList<>(List.of(divColab));
//            hospitais.forEach(c -> {
//                Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), UtilSecurity.decryptId(c));
//                conjunction[0] = builder().or(conjunction[0], equal);
//            });
//        }
//        if(params.getServico() != null){
//            String[] divColab = params.getServico().split("_");
//            Collection<String> servicos = new ArrayList<>(List.of(divColab));
//            servicos.forEach(c -> {
//                Predicate equal = builder().equal(joinServico.get(Servico_.ID), UtilSecurity.decryptId(c));
//                conjunction[0] = builder().or(conjunction[0], equal);
//            });
//        }
//        criteria.where(conjunction);
        TypedQuery<Cobertura> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Cobertura> getClazz() {
        return Cobertura.class;
    }
}
