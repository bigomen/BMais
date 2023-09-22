package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.restmodel.RestServicoPesquisa;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

@org.springframework.stereotype.Repository
public class ServicoRepositoryCustomImpl extends Repository<Servico> implements ServicoRepositoryCustom
{
    @Override
    public Collection<Servico> listarServicos(RestServicoPesquisa params){
        CriteriaQuery<Servico> criteria = builder().createQuery(Servico.class);
        Root<Servico> root = criteria.from(Servico.class);
        Collection<Predicate> predicates = new ArrayList<Predicate>();

        Path<Long> id = root.get("id");
        Path<String> codigo = root.get("codigo");
        Path<String> descricao = root.get("descricao");
        Path<Boolean> editavel = root.get("editavel");

        predicates.add(builder().equal(root.get("clientePrestador"), params.getClientePrestador()));

        if(StringUtils.isNotBlank(params.getCodigo())){
            predicates.add(builder().like(builder().upper(root.get("codigo")), like(params.getCodigo().toUpperCase())));
        }

        if(StringUtils.isNotBlank(params.getDescricao())){
            predicates.add(builder().like(builder().upper(root.get("descricao")), like(params.getDescricao().toUpperCase())));
        }

        criteria.multiselect(id, codigo, descricao, editavel);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.orderBy(builder().asc(descricao));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Class<Servico> getClazz() {
        return Servico.class;
    }
}
