package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.AcomodacaoVisita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcomodacaoVisitaRepository extends CrudRepository<AcomodacaoVisita, Long> {

    @Query("select new AcomodacaoVisita(av.id, av.acomodacao, av.dataInicio, av.dataFim) from AcomodacaoVisita av join av.acomodacao a join av.visita v where v.id = :id order by av.dataInicio")
    Collection<AcomodacaoVisita> getAcomodacoes(Long id);
}
