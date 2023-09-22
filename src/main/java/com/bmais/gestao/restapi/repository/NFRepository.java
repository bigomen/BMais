package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.repository.custom.NFRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFRepository extends CrudRepository<NF, Long>, NFRepositoryCustom {
}
