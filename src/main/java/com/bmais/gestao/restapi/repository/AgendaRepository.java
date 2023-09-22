package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.repository.custom.AgendaRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long>, AgendaRepositoryCustom {
}
