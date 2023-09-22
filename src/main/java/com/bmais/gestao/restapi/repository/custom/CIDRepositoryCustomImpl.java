package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.model.StatusCID;
import com.bmais.gestao.restapi.restmodel.RestCIDPesquisa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CIDRepositoryCustomImpl extends Repository<CID> implements CIDRepositoryCustom{

    @Override
    public Page<CID> lista(int pagina, RestCIDPesquisa params){
        CriteriaQuery<CID> criteria = builder().createQuery(CID.class);
        CriteriaQuery<Long> criteriaCount = builder().createQuery(Long.class);
        Root<CID> root = criteria.from(CID.class);
        Root<CID> rootCount = criteriaCount.from(CID.class);
        Collection<Predicate> predicates = new ArrayList<>();
        Pageable pageable = PageRequest.of(pagina-1, params.getPageSize());
        List<Order> orderList = new ArrayList<>();

        if(StringUtils.isNotBlank(params.getCodigo())){
            predicates.add(builder().like(builder().upper(root.get("codigo")), like(params.getCodigo().toUpperCase())));
        }

        if(StringUtils.isNotBlank(params.getDescricao())){
            predicates.add(builder().like(builder().upper(root.get("descricao")), like(params.getDescricao().toUpperCase())));
        }

        if(StringUtils.isNotBlank(params.getStatus())){
            Join<CID, StatusCID> joinStatus = root.join("status");
            predicates.add(builder().equal(joinStatus.get("id"), params.getStatus()));
            rootCount.join("status");
        }

        criteriaCount.select(builder().count(root.get("id")));
        criteriaCount.where(predicates.toArray(new Predicate[0]));
        criteria.where(predicates.toArray(new Predicate[0]));
        orderList.add(builder().asc(root.get("descricao")));
        orderList.add(builder().asc(root.get("codigo")));
        criteria.orderBy(orderList);

        int setFirst = pageable.getPageNumber() * pageable.getPageSize();

        List<CID> cidList = entityManager.createQuery(criteria).setMaxResults(pageable.getPageSize()).setFirstResult(setFirst).getResultList();
        Long count = entityManager.createQuery(criteriaCount).getSingleResult();
        return new PageImpl<>(cidList, pageable, count);
    }

    @Override
    public Class<CID> getClazz(){
        return CID.class;
    }
}
