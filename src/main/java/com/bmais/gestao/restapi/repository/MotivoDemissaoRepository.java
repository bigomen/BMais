package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.MotivoDemissao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoDemissaoRepository extends CrudRepository<MotivoDemissao, Long> {

    @Query("select c.motivoDemissao from Colaborador c where c.id = :id")
    MotivoDemissao getMotivoDemissao(Long id);


}
