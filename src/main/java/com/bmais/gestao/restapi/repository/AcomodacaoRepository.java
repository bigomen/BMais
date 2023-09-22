package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Acomodacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcomodacaoRepository extends CrudRepository<Acomodacao, Long> {
}
