package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Ferias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FeriasRepository extends CrudRepository<Ferias, Long> {

    @Query("select new Ferias(f.id, f.dataInicio, f.dataFim, f.gozoInicio1, f.gozoFim1, f.gozoInicio2, f.gozoFim2, f.gozoInicio3, f.gozoFim3, f.vendasDias, f.quantidadeDias, f.status) from Ferias f join f.colaborador c where c.id = :id order by f.dataInicio")
    Collection<Ferias> feriasColaborador(Long id);

    @Query("select f from Ferias f join f.colaborador c where c.id = :id order by f.dataInicio")
    Collection<Ferias> feriasColab(Long id);
}
