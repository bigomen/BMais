package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Impostos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ImpostosRepository extends CrudRepository<Impostos, Long> {

    @Query("select i from Impostos i order by i.descricao")
    Collection<Impostos> valores();
}
