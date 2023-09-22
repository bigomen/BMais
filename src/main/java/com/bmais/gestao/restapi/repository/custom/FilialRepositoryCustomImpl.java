//package com.bmais.gestao.restapi.repository.custom;
//
//import com.bmais.gestao.restapi.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.*;
//import java.util.Collection;
//
//public class FilialRepositoryCustomImpl extends Repository<Filial> implements FilialRepositoryCustom {
//
//    @Autowired
//    public FilialRepositoryCustomImpl(EntityManager em) {
//        super(em);
//    }
//
//    @Override
//    public Collection<Filial> pesquisar() {
//        CriteriaQuery<Filial> criteria = super.getCriteria();
//        Root<Filial> root = criteria.from(getClazz());
//        Predicate conjunction = builder().conjunction();
//        criteria.where(conjunction);
//        TypedQuery<Filial> typedQuery = entityManager.createQuery(criteria);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public Class<Filial> getClazz() {
//        return Filial.class;
//    }
//}
