package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.AuditoriaVisitaHomeCare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaVisitaHomeCareRepository extends CrudRepository<AuditoriaVisitaHomeCare, Long> {
}
