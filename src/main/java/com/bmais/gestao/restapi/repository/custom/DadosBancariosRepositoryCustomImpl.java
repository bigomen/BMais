package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class DadosBancariosRepositoryCustomImpl extends Repository<DadosBancarios> implements DadosBancariosRepositoryCustom{


    @Override
    public Collection<DadosBancarios> listar(Specification<DadosBancarios> spec){
        CriteriaQuery<DadosBancarios> criteria = super.getCriteria();
        Root<DadosBancarios> root = criteria.from(getClazz());
        Join<DadosBancarios, Banco> joinBanco = root.join(DadosBancarios_.BANCO);
        root.join(DadosBancarios_.EMPRESA, JoinType.INNER);

        Path<Long> idDadosBancarios = root.get(DadosBancarios_.ID);
        Path<Long> idBanco = joinBanco.get(Banco_.ID);
        Path<String> codBanco = joinBanco.get(Banco_.CODIGO);
        Path<String> nomeBanco = joinBanco.get(Banco_.NOME);
        Path<String> agencia = root.get(DadosBancarios_.AGENCIA);
        Path<String> conta = root.get(DadosBancarios_.CONTA);
        Path<Boolean> status = root.get(DadosBancarios_.ATIVO);
        Path<BigDecimal> saldo = root.get(DadosBancarios_.SALDO);

        criteria.multiselect(idDadosBancarios, idBanco, codBanco, nomeBanco, agencia, conta, status, saldo);
        criteria.where(spec.toPredicate(root, criteria, super.builder()));
        criteria.orderBy(builder().asc(nomeBanco));
        TypedQuery<DadosBancarios> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<DadosBancarios> pesquisarPorId(Long id)
    {
        CriteriaQuery<DadosBancarios> criteria = super.getCriteria();
        Root<DadosBancarios> root = criteria.from(getClazz());
        root.fetch(DadosBancarios_.BANCO, JoinType.INNER);
        root.fetch(DadosBancarios_.ENDERECO, JoinType.INNER);
        root.fetch(DadosBancarios_.EMPRESA, JoinType.INNER);

        criteria.where(builder().equal(root.get(DadosBancarios_.ID), id));
        TypedQuery<DadosBancarios> typedQuery = entityManager.createQuery(criteria);

        try
        {
            return Optional.of(typedQuery.getSingleResult());
        }catch (NoResultException nex)
        {
            return Optional.empty();
        }
    }

    @Override
    public Class<DadosBancarios> getClazz(){return DadosBancarios.class;}
}
