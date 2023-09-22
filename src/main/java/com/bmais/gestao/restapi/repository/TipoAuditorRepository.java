package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.TipoAuditor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface TipoAuditorRepository extends CrudRepository<TipoAuditor, Long>
{
    Collection<TipoAuditor> findAllByOrderByDescricao();
}
