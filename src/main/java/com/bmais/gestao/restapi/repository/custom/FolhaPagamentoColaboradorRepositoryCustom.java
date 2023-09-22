package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorPesquisa;

import java.util.Collection;

public interface FolhaPagamentoColaboradorRepositoryCustom {

    Collection<FolhaPagamentoColaborador> lista(RestFolhaPagamentoColaboradorPesquisa params);
}
