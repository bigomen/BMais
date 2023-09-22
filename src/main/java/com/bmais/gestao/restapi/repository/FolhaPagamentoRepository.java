package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.FolhaPagamento;
import com.bmais.gestao.restapi.repository.custom.FolhaPagamentoRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolhaPagamentoRepository extends CrudRepository<FolhaPagamento, Long>, FolhaPagamentoRepositoryCustom {

    @Query("select new FolhaPagamento(f.id,  f.dataVencimento, f.dataEmissao, e.id, e.razaoSocial, d.id, d.banco, d.agencia, d.conta, u.id, u.email) " +
            "from FolhaPagamento f join f.empresa e join f.dadosBancarios d join f.usuario u where f.id = :id")
    FolhaPagamento buscarFolha(Long id);
}
