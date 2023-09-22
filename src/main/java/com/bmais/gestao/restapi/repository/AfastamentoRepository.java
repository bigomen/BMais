package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Afastamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AfastamentoRepository extends CrudRepository<Afastamento, Long> {

    @Query("select new Afastamento(a.id, a.tipo, a.dataInicio, a.dataPrevisao, a.diasPrevistos, a.dataRetorno, a.diasReais, c.id, c.descricao) from Afastamento a join a.cid c where a.colaborador.id = :id order by a.dataInicio")
    Collection<Afastamento> afastamentosColaborador(Long id);
}
