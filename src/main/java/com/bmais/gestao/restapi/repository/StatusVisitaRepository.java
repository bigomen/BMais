package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.StatusVisita;

@Repository
public interface StatusVisitaRepository extends CrudRepository<StatusVisita, Long>
{
	Collection<StatusVisita> findAll();
}
