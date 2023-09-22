package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Internacao;
import com.bmais.gestao.restapi.repository.custom.InternacaoRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface InternacaoRepository extends CrudRepository<Internacao, Long>, InternacaoRepositoryCustom {

    @Query(value = "select i from Internacao i join fetch i.paciente p join fetch i.hospital h join fetch i.status s where i.id = :id")
    Optional<Internacao> findById(@Param(value = "id") Long id);

//    @Query("select i.numeroInternacao from Internacao i where i.id = :id")
//    Long getNumeroInternacao(Long id);

    @Modifying
    @Query("update Internacao i set i.status.id = 3L where i.id = :id")
    void desativar(Long id);

    @Query("select MAX(i.dataHora) from Internacao i where i.paciente.id = :id")
    LocalDate ultimaInternacao(Long id);

    @Modifying
    @Query("update Internacao i set i.paciente.id = :idPrincipal where i.paciente.id = :id")
    void alterarPaciente(Long id, Long idPrincipal);

    @Query("select i from Internacao i join i.paciente p where p.id = :id")
    Collection<Internacao> getInternacoes(Long id);

    @Query("select (case when count(p.id) > 0 then true else false end) from Internacao i join i.paciente p join i.status s where s.id = 1L and p.id = :pacienteId")
    Boolean validarInternacao(Long pacienteId);
}
