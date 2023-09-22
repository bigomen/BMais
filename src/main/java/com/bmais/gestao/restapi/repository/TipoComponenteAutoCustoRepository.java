package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.TipoComponente;

@Repository
public interface TipoComponenteAutoCustoRepository extends CrudRepository<TipoComponente, Long>
{
	Collection<TipoComponente> findAll(Sort by);
}
