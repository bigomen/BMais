package com.bmais.gestao.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.TipoMovimentacaoBancaria;

@Repository
public interface TipoMovimentacaoBancariaRepository extends CrudRepository<TipoMovimentacaoBancaria, Long>
{
    Iterable<TipoMovimentacaoBancaria> findAllByOrderByDescricaoAsc();
}
