package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.restmodel.RestColaboradorPesquisa;

import java.util.Collection;

public interface ColaboradorRepositoryCustom {

    Collection<Colaborador> lista(RestColaboradorPesquisa params);

    Colaborador detalhes(Long id);

    Collection<Colaborador> pesquisarColaboradoresPorEmpresa(Empresa empresa);
}
