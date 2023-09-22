package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.TipoAfastamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TipoAfastamentoRepository extends CrudRepository<TipoAfastamento, Long> {

    Collection<TipoAfastamento> findAllByOrderByDescricaoAsc();
}
