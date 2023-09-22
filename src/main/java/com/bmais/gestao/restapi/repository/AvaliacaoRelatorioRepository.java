package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.AvaliacaoRelatorio;

@Repository
public interface AvaliacaoRelatorioRepository extends CrudRepository<AvaliacaoRelatorio, Long> {
	
	@Query(value = "select r from AvaliacaoRelatorio r join fetch r.visitas v where v.id = :visita order by r.descricao")
	List<AvaliacaoRelatorio> pesquisarAvaliacoes(@Param(value = "visita") Long visita);
	
	Collection<AvaliacaoRelatorio> findAll(Sort sort);
}
