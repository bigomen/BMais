package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Fornecedor;
import com.bmais.gestao.restapi.restmodel.RestFornecedorPesquisa;

import java.util.Collection;

public interface FornecedorRepositoryCustom {

    Collection<Fornecedor> pesquisarFornecedor(RestFornecedorPesquisa params);

    Fornecedor detalhes(Long id);
}
