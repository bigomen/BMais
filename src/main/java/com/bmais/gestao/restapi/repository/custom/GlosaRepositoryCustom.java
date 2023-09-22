package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.model.enums.Responsabilidade;

import java.util.Collection;

public interface GlosaRepositoryCustom {

    Collection<Glosa> pesquisar(String codigo, Responsabilidade responsabilidade);
}
