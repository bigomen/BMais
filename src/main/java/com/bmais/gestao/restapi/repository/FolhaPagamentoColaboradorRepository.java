package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.bmais.gestao.restapi.repository.custom.FolhaPagamentoColaboradorRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FolhaPagamentoColaboradorRepository extends CrudRepository<FolhaPagamentoColaborador, Long>, FolhaPagamentoColaboradorRepositoryCustom {

    @Query("select fp.id from FolhaPagamentoColaborador fpc join fpc.folhaPagamento fp where fpc.id = :id")
    Long buscarIdFolha(Long id);

    @Query("select new FolhaPagamentoColaborador(fpc.id, c.id, c.nome, t.id, t.descricao, fpc.valor) from FolhaPagamentoColaborador fpc " +
            "join fpc.colaborador c join fpc.tipoPagamento t where fpc.folhaPagamento.id = :id")
    Collection<FolhaPagamentoColaborador> buscarFolhas(Long id);

//    @Query("select new PagamentosGerados(e.razaoSocial, p.dataEmissao, p.dataVencimento, b.nome, p.dataPagamento, p.valor," +
//            "p.valorPago, pl. )")
}
