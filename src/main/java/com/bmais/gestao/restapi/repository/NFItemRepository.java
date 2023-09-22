package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.model.NFItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NFItemRepository extends CrudRepository<NFItem, Long> {

    Collection<NFItem> findByNf(NF nf);
}
