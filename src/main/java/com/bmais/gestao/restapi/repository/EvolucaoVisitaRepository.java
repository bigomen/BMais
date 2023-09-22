package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.EvolucaoVisita;
import com.bmais.gestao.restapi.model.Visita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EvolucaoVisitaRepository extends CrudRepository<EvolucaoVisita, Long> 
{
	Collection<EvolucaoVisita> findByVisitaIdOrderByDataEvolucaoDesc(Long id);

	EvolucaoVisita findByVisita(Visita visita);

	@Query("select e from EvolucaoVisita e join e.visita v where v.internacao.id = :idInternacao order by e.dataEvolucao desc")
	Collection<EvolucaoVisita> evolucoesInternacao(Long idInternacao);

	void deleteByVisitaId(Long visitaId);

}
