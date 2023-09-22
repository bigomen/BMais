package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.model.UF;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface UfRepository extends CrudRepository<UF, Long> {

    Collection<UF> findAllByOrderByDescricaoAsc();

}
