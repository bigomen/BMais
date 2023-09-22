package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.model.Glosa_;
import com.bmais.gestao.restapi.model.enums.Responsabilidade;

public class GlosaRepositoryCustomImpl extends Repository<Glosa> implements GlosaRepositoryCustom {


    @Override
    public Collection<Glosa> pesquisar(String codigo, Responsabilidade responsabilidade) {
        CriteriaQuery<Glosa> criteria = super.getCriteria();
        Root<Glosa> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        if (StringUtils.isNotBlank(codigo)) {
            Predicate like = builder().like(builder().upper(root.get(Glosa_.CODIGO)), like((codigo.toUpperCase())));
            conjunction = builder().and(conjunction, like);
        }
        if (responsabilidade != null) {
            Predicate equal = builder().equal(root.get(Glosa_.RESPONSABILIDADE), responsabilidade);
            conjunction = builder().and(conjunction, equal);
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(root.get("descricao")));
        TypedQuery<Glosa> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Glosa> getClazz() {
        return Glosa.class;
    }
}
