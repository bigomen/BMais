package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.JustificativaPermanencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificativaPermanenciaRepository extends CrudRepository<JustificativaPermanencia, Long> {
}
