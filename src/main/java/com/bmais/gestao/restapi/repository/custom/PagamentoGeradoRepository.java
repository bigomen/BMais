package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.PagamentosGerados;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoGeradoRepository extends CrudRepository<PagamentosGerados, Long> {

    @Query("select new PagamentosGerados(p.id, p.lancamento, p.dataVencimento, p.documento, p.valorPago, p.historico, c.id, c.razaoSocial)" +
            " from PagamentosGerados p join p.cliente c where p.folhaPagamentoColaborador.id = :id")
    PagamentosGerados buscarPagamento(Long id);
}
