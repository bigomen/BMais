package com.bmais.gestao.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.TipoAgenda;

import java.util.Collection;

@Repository
public interface TipoAgendaRepository extends CrudRepository<TipoAgenda, Long>
{
    Collection<TipoAgenda> findAllByOrderByDescricaoAsc();
}
