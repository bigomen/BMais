package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Avaliacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {
}
