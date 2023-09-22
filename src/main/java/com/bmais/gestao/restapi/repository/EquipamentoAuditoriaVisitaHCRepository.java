package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.EquipamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EquipamentoAuditoriaVisitaHCRepository extends CrudRepository<EquipamentoAuditoriaVisitaHC, Long> {

    @Query("select new EquipamentoAuditoriaVisitaHC(e.id, e.equipamento, e.proprio, e.observacao) from EquipamentoAuditoriaVisitaHC e where e.visita = :visita order by e.equipamento")
    Collection<EquipamentoAuditoriaVisitaHC> equipamentosAuditoria(VisitaHomeCare visita);
}
