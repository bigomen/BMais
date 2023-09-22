package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.PagamentosGerados;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentosGeradosRepository extends CrudRepository<PagamentosGerados, Long> {

    @Query("select new PagamentosGerados(p.id, p.lancamento, p.movimentacaoBancaria, p.dataEmissao, p.dataVencimento, " +
            "p.documento, p.valorPago, p.historico, c.id, c.razaoSocial, p.status) from PagamentosGerados p join p.cliente c " +
            "where p.folhaPagamentoColaborador.id = :id")
    PagamentosGerados buscarPagamentos(Long id);
}
