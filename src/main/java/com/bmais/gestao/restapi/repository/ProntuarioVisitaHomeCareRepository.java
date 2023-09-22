package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.repository.custom.ProntuarioVisitaHomeCareRepositoryCustom;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioVisitaHomeCareRepository extends CrudRepository<ProntuarioVisitaHomeCare, Long>, ProntuarioVisitaHomeCareRepositoryCustom {
	 @Query(value = "select i from ProntuarioVisitaHomeCare i join fetch i.paciente p join fetch i.status s where i.id = :id")
	    Optional<ProntuarioVisitaHomeCare> findById(@Param(value = "id") Long id);

//	    @Query("select i.numeroInternacao from Internacao i where i.id = :id")
//	    Long getNumeroInternacao(Long id);

	    @Modifying
	    @Query("update ProntuarioVisitaHomeCare i set i.status.id = 3L where i.id = :id")
	    void desativar(Long id);

	    @Query("select MAX(i.inicio) from ProntuarioVisitaHomeCare i where i.paciente.id = :id")
	    LocalDate ultimoProntuario(Long id);

	    @Modifying
	    @Query("update ProntuarioVisitaHomeCare i set i.paciente.id = :idPrincipal where i.paciente.id = :id")
	    void alterarPaciente(Long id, Long idPrincipal);
}
