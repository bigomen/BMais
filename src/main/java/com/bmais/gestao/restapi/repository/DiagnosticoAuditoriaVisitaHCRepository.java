package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.DiagnosticoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DiagnosticoAuditoriaVisitaHCRepository extends CrudRepository<DiagnosticoAuditoriaVisitaHC, Long> {

    @Query("select new DiagnosticoAuditoriaVisitaHC(d.id, d.principal, c.id, c.descricao) from DiagnosticoAuditoriaVisitaHC d join d.cid c where d.visita = :visita order by c.descricao")
    Collection<DiagnosticoAuditoriaVisitaHC> diagnosticosAuditoria(VisitaHomeCare visita);
}
