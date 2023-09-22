package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Dependente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DependenteRepository extends CrudRepository<Dependente, Long> {

    @Query("select new Dependente(d.id, d.nome, d.nascimento, d.rg, d.cpf, d.sus) from Dependente d where d.colaborador.id = :id order by d.nome")
    Collection<Dependente> dependentesColaborador(Long id);
}
