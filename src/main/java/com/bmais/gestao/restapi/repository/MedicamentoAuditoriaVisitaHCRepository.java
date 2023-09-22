package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.MedicamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MedicamentoAuditoriaVisitaHCRepository extends CrudRepository<MedicamentoAuditoriaVisitaHC, Long> {

    @Query("select new MedicamentoAuditoriaVisitaHC(m.id, m.origem, m.descricao, m.medicamento, m.via, m.frequencia, m.periodo) from MedicamentoAuditoriaVisitaHC m where m.visita = :visita order by m.descricao")
    Collection<MedicamentoAuditoriaVisitaHC> medicamentosAuditoria(VisitaHomeCare visita);
}
