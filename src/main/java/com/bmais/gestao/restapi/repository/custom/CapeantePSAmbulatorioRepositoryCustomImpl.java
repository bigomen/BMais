package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.model.enums.TipoCapeante;
import com.bmais.gestao.restapi.model.projections.CapeantePSAmbulatorioProjection;
import com.bmais.gestao.restapi.repository.ResumoCapeantePSAmbulatorioRepository;
import com.bmais.gestao.restapi.restmodel.RestCapeantePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.*;

public class CapeantePSAmbulatorioRepositoryCustomImpl extends Repository<CapeantePSAmbulatorio> implements CapeantePSAmbulatorioRepositoryCustom {

    private final ResumoCapeantePSAmbulatorioRepository resumoCapeantePSAmbulatorioRepository;

    public CapeantePSAmbulatorioRepositoryCustomImpl(ResumoCapeantePSAmbulatorioRepository resumoCapeantePSAmbulatorioRepository) {
        this.resumoCapeantePSAmbulatorioRepository = resumoCapeantePSAmbulatorioRepository;
    }

    @Override
    public Collection<CapeantePSAmbulatorioProjection> lista(RestCapeantePesquisa params){
        CriteriaQuery<CapeantePSAmbulatorioProjection> criteria = builder().createQuery(CapeantePSAmbulatorioProjection.class);
        Root<CapeantePSAmbulatorio> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();
        List<Order> orderList = new ArrayList<>();
        
        Join<CapeantePSAmbulatorio, StatusCapeante> joinStatus = root.join(CapeantePSAmbulatorio_.STATUS, JoinType.INNER);
        Join<CapeantePSAmbulatorio, Hospital> joinHospital = root.join(CapeantePSAmbulatorio_.HOSPITAL);
        Join<CapeantePSAmbulatorio, Cliente> joinCliente = root.join(CapeantePSAmbulatorio_.CLIENTE);

        Path<Long> idCapeante = root.get(CapeantePSAmbulatorio_.ID);
        Path<String> razaoHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<String> loteProtocolo = root.get(CapeantePSAmbulatorio_.LOTE_PROTOCOLO);
        Path<String> statusCapeante = joinStatus.get(StatusCapeante_.DESCRICAO);
        Path<Date> dataFechamento = root.get(CapeantePSAmbulatorio_.DATA_FECHAMENTO);
        
        Subquery<BigDecimal> subValorCobrado = criteria.subquery(BigDecimal.class);
        Root<ResumoCapeantePSAmbulatorio> valorCobradoFrom = subValorCobrado.from(ResumoCapeantePSAmbulatorio.class);
        Expression<BigDecimal> valorCobrado = builder().sum(valorCobradoFrom.get(ResumoCapeantePSAmbulatorio_.VALOR_COBRADO));
        subValorCobrado.select(valorCobrado);
        subValorCobrado.where(builder().equal(valorCobradoFrom.get(ResumoCapeantePSAmbulatorio_.CAPEANTE_PS_AMBULATORIO).get(CapeantePSAmbulatorio_.ID), idCapeante));
        
        Subquery<BigDecimal> subValorGlosado = criteria.subquery(BigDecimal.class);
        Root<ResumoCapeantePSAmbulatorio> valorGlosadoFrom = subValorGlosado.from(ResumoCapeantePSAmbulatorio.class);
        Expression<BigDecimal> valorGlosado = builder().sum(valorGlosadoFrom.get(ResumoCapeantePSAmbulatorio_.VALOR_GLOSADO));
        subValorGlosado.select(valorGlosado);
        subValorGlosado.where(builder().equal(valorGlosadoFrom.get(ResumoCapeantePSAmbulatorio_.CAPEANTE_PS_AMBULATORIO).get(CapeantePSAmbulatorio_.ID), idCapeante));
        
        Subquery<BigDecimal> subValorLiberado = criteria.subquery(BigDecimal.class);
        Root<ResumoCapeantePSAmbulatorio> valorLiberadoFrom = subValorLiberado.from(ResumoCapeantePSAmbulatorio.class);
        Expression<BigDecimal> valorLiberado = builder().sum(valorLiberadoFrom.get(ResumoCapeantePSAmbulatorio_.VALOR_LIBERADO));
        subValorLiberado.select(valorLiberado);
        subValorLiberado.where(builder().equal(valorLiberadoFrom.get(ResumoCapeantePSAmbulatorio_.CAPEANTE_PS_AMBULATORIO).get(CapeantePSAmbulatorio_.ID), idCapeante));

        criteria.multiselect(idCapeante, razaoHospital, razaoCliente, loteProtocolo, statusCapeante, dataFechamento, subValorCobrado, subValorGlosado, subValorLiberado);
        
        if(StringUtils.isNotBlank(params.getHospital()))
        {
            Predicate equalHospital = builder().equal(joinHospital.get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
            conjunction = builder().and(conjunction, equalHospital);
        }
        
        if(StringUtils.isNotBlank(params.getCliente()))
        {
            Predicate equalCliente = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equalCliente);
        }
        
        if(StringUtils.isNotBlank(params.getLoteProtocolo()))
        {
            Predicate equalProtocolo = builder().equal(root.get(CapeantePSAmbulatorio_.LOTE_PROTOCOLO), params.getLoteProtocolo());
            conjunction = builder().and(conjunction, equalProtocolo);
        }
        
        if(params.getTipo() != null)
        {
            Predicate equalTipo = builder().equal(root.get(CapeantePSAmbulatorio_.TIPO_CAPEANTE), params.getTipo());
            conjunction = builder().and(conjunction, equalTipo);
        }
        
        if(StringUtils.isNotBlank(params.getStatus()))
        {
            Predicate equalStatus = builder().equal(root.get(CapeantePSAmbulatorio_.STATUS).get(StatusCapeante_.ID), Long.parseLong(params.getStatus()));
            conjunction = builder().and(conjunction, equalStatus);
        }
        
        if(StringUtils.isNotBlank(params.getNumero()))
        {
            Predicate likeNumero = builder().like(idCapeante.as(String.class), like(params.getNumero()));
            conjunction = builder().and(conjunction, likeNumero);
//        	Predicate equalNumero = builder().equal(root.get(CapeantePSAmbulatorio_.ID), Long.parseLong(params.getNumero()));
//        	conjunction = builder().and(conjunction, equalNumero);
        }
        
        if(params.getDataInicialFechamento() != null && params.getDataFinalFechamento() !=null)
        {
            Predicate between = builder().between(root.get(CapeantePSAmbulatorio_.DATA_FECHAMENTO), params.getDataInicialFechamento(), params.getDataFinalFechamento());
            conjunction = builder().and(conjunction, between);
        }

        criteria.where(conjunction);
        orderList.add(builder().asc(razaoCliente));
        orderList.add(builder().asc(razaoHospital));
        criteria.orderBy(orderList);
        
        TypedQuery<CapeantePSAmbulatorioProjection> typedQuery = entityManager.createQuery(criteria);
        Collection<CapeantePSAmbulatorioProjection> capeante = typedQuery.getResultList();
        return capeante;
    }

    @Override
    public Optional<CapeantePSAmbulatorio> detalhes(Long id){
        CriteriaQuery<CapeantePSAmbulatorio> criteria = super.getCriteria();
        Root<CapeantePSAmbulatorio> root = criteria.from(getClazz());
        criteria.where(builder().equal(root.get(Auditor_.ID), id));

        Join<CapeantePSAmbulatorio, Usuario> joinUsuario = root.join(CapeantePSAmbulatorio_.USUARIO);
        Join<CapeantePSAmbulatorio, Hospital> joinHospital = root.join(CapeantePSAmbulatorio_.HOSPITAL);
        Join<CapeantePSAmbulatorio, Cliente> joinCliente = root.join(CapeantePSAmbulatorio_.CLIENTE);
        Join<CapeantePSAmbulatorio, Auditor> joinAuditorMedico = root.join(CapeantePSAmbulatorio_.AUDITOR_MEDICO, JoinType.LEFT);
        Join<CapeantePSAmbulatorio, Auditor> joinAuditorEnfermeiro = root.join(CapeantePSAmbulatorio_.AUDITOR_ENFERMEIRO, JoinType.LEFT);

        Path<Long> idCapeante = root.get(CapeantePSAmbulatorio_.ID);
        Path<TipoCapeante> tipoCapeante = root.get(CapeantePSAmbulatorio_.TIPO_CAPEANTE);
        Path<String> loteProtocolo = root.get(CapeantePSAmbulatorio_.LOTE_PROTOCOLO);
        Path<StatusCapeante> statusCapeante = root.get(CapeantePSAmbulatorio_.STATUS);
        Path<Long> idUsuario = joinUsuario.get(Usuario_.ID);
        Path<String> emailUsuario = joinUsuario.get(Usuario_.EMAIL);
        Path<Long> idHospital = joinHospital.get(Hospital_.ID);
        Path<String> razaoHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<Date> dataAbertura = root.get(CapeantePSAmbulatorio_.DATA_ABERTURA);
        Path<Date> dataFechamento = root.get(CapeantePSAmbulatorio_.DATA_FECHAMENTO);
        Path<Long> idAuditorMedico = joinAuditorMedico.get(Auditor_.ID);
        Path<String> nomeAuditorMedico = joinAuditorMedico.get(Auditor_.NOME);
        Path<Long> idAuditorEnfermeiro = joinAuditorEnfermeiro.get(Auditor_.ID);
        Path<String> nomeAuditorEnfermeiro = joinAuditorEnfermeiro.get(Auditor_.NOME);
        Path<String> observacao = root.get(CapeantePSAmbulatorio_.OBSERVACAO);

        criteria.multiselect(idCapeante, tipoCapeante, loteProtocolo, statusCapeante, idUsuario, emailUsuario, idHospital, razaoHospital, idCliente, razaoCliente,
                dataAbertura, dataFechamento, idAuditorMedico, nomeAuditorMedico, idAuditorEnfermeiro, nomeAuditorEnfermeiro, observacao);

        TypedQuery<CapeantePSAmbulatorio> typedQuery = entityManager.createQuery(criteria);
        try
        {
            CapeantePSAmbulatorio capeante = typedQuery.getSingleResult();
            capeante.setResumo(resumoCapeantePSAmbulatorioRepository.getResumos(capeante.getId()));
            return Optional.of(capeante);
        }catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public Class<CapeantePSAmbulatorio> getClazz() {
        return CapeantePSAmbulatorio.class;
    }
}
