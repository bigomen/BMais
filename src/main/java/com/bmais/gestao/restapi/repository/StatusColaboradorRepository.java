package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.StatusColaborador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusColaboradorRepository extends CrudRepository<StatusColaborador, Long> {
}
