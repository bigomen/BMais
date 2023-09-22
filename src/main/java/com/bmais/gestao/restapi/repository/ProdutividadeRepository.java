package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Produtividade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutividadeRepository extends CrudRepository<Produtividade, Long> {
}
