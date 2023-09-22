package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.FolhaPagamento;
import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorPesquisa;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class FolhaPagamentoRepositoryCustomImpl extends com.bmais.gestao.restapi.repository.custom.Repository<FolhaPagamento> implements FolhaPagamentoRepositoryCustom {

    public Long registrarDocumento(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_pagamentos_gerados_documento')")
                .getSingleResult().toString());
    }

    public Long movimentacaoBancaria(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_movimentacao_bancaria')")
                .getSingleResult().toString());
    }


    @Override
    public Class<FolhaPagamento> getClazz(){return  FolhaPagamento.class;}
}
