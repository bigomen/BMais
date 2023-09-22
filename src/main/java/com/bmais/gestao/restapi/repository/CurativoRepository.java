package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Curativo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CurativoRepository extends CrudRepository<Curativo, Long> {

    @Query("select new Curativo(c.id, c.local, c.complexidade, c.tamanho) from Curativo c where c.visita.id = :id order by c.local")
    Collection<Curativo> curativosVisita(Long id);
}
