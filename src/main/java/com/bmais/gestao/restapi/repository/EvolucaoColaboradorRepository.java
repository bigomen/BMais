package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.EvolucaoColaborador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EvolucaoColaboradorRepository extends CrudRepository<EvolucaoColaborador, Long> {

    @Query("select new EvolucaoColaborador(e.id, e.dataInicio, e.areaSetor, e.descricao, e.salario, c.id, c.descricao) from EvolucaoColaborador e join e.cargo c where e.colaborador.id = :id order by e.dataInicio")
    Collection<EvolucaoColaborador> evolucoesColaborador(Long id);
}
