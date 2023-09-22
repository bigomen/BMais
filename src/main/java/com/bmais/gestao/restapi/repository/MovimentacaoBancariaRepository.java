package com.bmais.gestao.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;
import com.bmais.gestao.restapi.repository.custom.MovimentacaoBancariaRepositoryCustom;

@Repository
public interface MovimentacaoBancariaRepository extends CrudRepository<MovimentacaoBancaria, Long>, MovimentacaoBancariaRepositoryCustom
{

}
