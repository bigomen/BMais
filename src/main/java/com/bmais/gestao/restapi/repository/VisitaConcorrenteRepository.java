package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Visita;
import com.bmais.gestao.restapi.repository.custom.VisitaConcorrenteRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaConcorrenteRepository extends CrudRepository<Visita, Long>, VisitaConcorrenteRepositoryCustom 
{
	@Query("select i.id from Visita v join v.internacao i where v.id = :id")
    Long getInternacaoId(Long id);

    @Query("select CASE WHEN count(i.id) > 0 THEN TRUE ELSE FALSE END from Visita v join v.internacao i where i.id = :internacaoId and v.id > :visitaId")
    Boolean validarExclusao(Long internacaoId, Long visitaId);
}
