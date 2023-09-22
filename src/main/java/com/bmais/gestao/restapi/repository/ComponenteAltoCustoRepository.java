package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ComponenteAltoCusto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComponenteAltoCustoRepository extends CrudRepository<ComponenteAltoCusto, Long> {

    @Query("select new ComponenteAltoCusto(c. id, c.tipo, c.marcaTipo, c.quantidade) from ComponenteAltoCusto c where c.visita.id = :id order by c.marcaTipo")
    Collection<ComponenteAltoCusto> componentesVisita(Long id);
}
