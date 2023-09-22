package com.bmais.gestao.restapi.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class Repository <T> {

    protected EntityManager entityManager;
    public abstract Class<T> getClazz();

    protected CriteriaBuilder builder() {
        return entityManager.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> getCriteria() {
        CriteriaQuery<T> query = builder().createQuery(getClazz());
        return query;
    }

    @Autowired
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected String like(String parametro) {
        return "%".concat(parametro).concat("%");
    }
}
