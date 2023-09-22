package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.MaterialAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MaterialAuditoriaVisitaHCRepository extends CrudRepository<MaterialAuditoriaVisitaHC, Long> {
    @Query("select new MaterialAuditoriaVisitaHC(m.id, m.material, m.quantidade, m.frequencia, m.produto, m.observacao) from MaterialAuditoriaVisitaHC m where m.visita = :visita order by m.material")
    Collection<MaterialAuditoriaVisitaHC> materiaisAuditoria(VisitaHomeCare visita);
}
