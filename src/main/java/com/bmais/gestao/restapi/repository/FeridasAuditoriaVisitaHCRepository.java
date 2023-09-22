package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.FeridasAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FeridasAuditoriaVisitaHCRepository extends CrudRepository<FeridasAuditoriaVisitaHC, Long> {

    @Query("select new FeridasAuditoriaVisitaHC(f.id, f.local, f.tamanho, f.aspecto) from FeridasAuditoriaVisitaHC f where f.visita = :visita order by f.local")
    Collection<FeridasAuditoriaVisitaHC> feridasAuditoria(VisitaHomeCare visita);
}
