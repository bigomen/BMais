package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.VisitaHomeCare;
import com.bmais.gestao.restapi.repository.custom.VisitaHomecareRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface VisitaHomecareRepository extends CrudRepository<VisitaHomeCare, Long>, VisitaHomecareRepositoryCustom {

    @Modifying
    @Query("update VisitaHomeCare v set v.status.id = :status where v.id = :id")
    void atualizarStatus(Long id, Long status);

    @Query("select new VisitaHomeCare(v.numeroVisita) from VisitaHomeCare v where v.id = :id")
    VisitaHomeCare findNumero(Long id);

    @Query("select count(v.id) from VisitaHomeCare v join v.prontuario p where p.paciente.id = :id ")
    Long numeroVisitasPaciente(Long id);

    @Query("select MAX(v.dataInclusao) from VisitaHomeCare v join v.prontuario p where p.paciente.id = :id")
    LocalDate ultimaVisita(Long id);

    @Query("select MAX(v.numeroVisita) from VisitaHomeCare v join v.prontuario p where p.paciente.id = :id")
    Long numeroUltimaVisita(Long id);

    @Modifying
    @Query("update ProntuarioVisitaHomeCare p set p.paciente.id = :idPrincipal where p.paciente.id = :id")
    void alterarPaciente(Long id, Long idPrincipal);

    @Query("select new VisitaHomeCare(v.id, v.dataInclusao, v.numeroVisita, s.descricao) from VisitaHomeCare v join v.status s join v.prontuario p where p.paciente.id = :id order by v.dataInclusao")
    Collection<VisitaHomeCare> getRelatorio(Long id);

    @Query("select p.id from VisitaHomeCare vh join vh.prontuario p where vh.id = :idVisita")
    Long getProntuarioId(Long idVisita);

    @Query("select CASE WHEN count(p.id) > 0 THEN TRUE ELSE FALSE END from VisitaHomeCare vh join vh.prontuario p where p.id = :idProntuario and vh.id > :idVisita")
    Boolean validarExclusao(Long idVisita, Long idProntuario);
}
