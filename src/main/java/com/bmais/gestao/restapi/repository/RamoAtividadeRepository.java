package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.RamoAtividade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface RamoAtividadeRepository extends CrudRepository<RamoAtividade, Long> {

    Collection<RamoAtividade> findAllByOrderByDescricaoAsc();

    Boolean existsByDescricao(String descricao);
}
