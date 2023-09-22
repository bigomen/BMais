package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.restmodel.RestCoberturaPesquisa;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;

import java.util.Collection;

public interface CoberturaRepositoryCustom {

    Collection<Cobertura> lista(RestCoberturaPesquisa params);

    Collection<Cobertura> listaEscala(RestEscalaPesquisa params);

    Collection<Cobertura> listaCobertura(Long id);
}
