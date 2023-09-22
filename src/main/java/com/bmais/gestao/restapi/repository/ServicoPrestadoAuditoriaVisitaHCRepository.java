package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ServicoPrestadoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ServicoPrestadoAuditoriaVisitaHCRepository extends CrudRepository<ServicoPrestadoAuditoriaVisitaHC, Long> {

    @Query("select new ServicoPrestadoAuditoriaVisitaHC(s.id, s.profissional, s.quantidade, s.frequencia, s.servico, s.observacao, s.assistenciaAdequada) from ServicoPrestadoAuditoriaVisitaHC s where s.visita = :visita order by s.profissional")
    Collection<ServicoPrestadoAuditoriaVisitaHC> servicosAuditoria(VisitaHomeCare visita);
}
