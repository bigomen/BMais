package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
