package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.model.UF;

import java.util.List;

public interface UfRepositoryCustom {

    List<UF> listar();

    List<Municipio> buscar(Long id);

}
