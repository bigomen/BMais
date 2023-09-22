package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.StatusTuss;
import com.bmais.gestao.restapi.model.Tuss;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TussRepositoryCustomImpl extends Repository<Tuss> implements TussRepositoryCustom {

    @Override
    public Page<Tuss> pesquisar(String codigo, String descricao, Long status, String orderBy, int pagina, int pageSize) {
        CriteriaQuery<Tuss> criteria = builder().createQuery(Tuss.class);
        CriteriaQuery<Long> criteriaCount = builder().createQuery(Long.class);
        Root<Tuss> root = criteria.from(getClazz());
        Root<Tuss> rootCount = criteriaCount.from(Tuss.class);
        Collection<Predicate> predicates = new ArrayList<Predicate>();
        Pageable pageable = PageRequest.of(pagina-1, pageSize);

        if (StringUtils.isNotBlank(codigo)) {
            predicates.add(builder().like(builder().upper(root.get("codigo")), like(codigo.toUpperCase())));
        }
        if (StringUtils.isNotBlank(descricao)) {
            predicates.add(builder().like(builder().upper(root.get("descricao")),like(descricao.toUpperCase())));
        }
        if (status != null) {
            Join<Tuss, StatusTuss> joinStatus = root.join("status");
            predicates.add(builder().equal(joinStatus.get("id"), status));
            rootCount.join("status");
        }

        if(StringUtils.isNotBlank(orderBy)){
            criteria.orderBy(builder().asc(root.get(orderBy)));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        criteriaCount.select(builder().count(root.get("id")));
        criteriaCount.where(predicates.toArray(new Predicate[0]));

        int setFirst = pageable.getPageNumber() * pageable.getPageSize();

        List<Tuss> tussList = entityManager.createQuery(criteria).setFirstResult(setFirst).setMaxResults(pageable.getPageSize()).getResultList();
        Long count = entityManager.createQuery(criteriaCount).getSingleResult();
        return new PageImpl<>(tussList, pageable, count);
    }

    @Override
    public Class<Tuss> getClazz() {
        return Tuss.class;
    }
}
