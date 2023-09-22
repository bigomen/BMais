package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.StomiaAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StomiaAuditoriaVisitaHCRepository extends CrudRepository<StomiaAuditoriaVisitaHC, Long> {

    @Query("select new StomiaAuditoriaVisitaHC(s.id, s.stomia, s.observacao) from StomiaAuditoriaVisitaHC s where s.visita = :visita order by s.stomia")
    Collection<StomiaAuditoriaVisitaHC> stomiasAuditoria(VisitaHomeCare visita);
}
