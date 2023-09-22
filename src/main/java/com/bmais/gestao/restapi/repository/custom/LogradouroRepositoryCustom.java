package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Logradouro;

import java.util.List;

public interface LogradouroRepositoryCustom {

    List<Logradouro> listar();
    Logradouro buscar(Long id);
}
