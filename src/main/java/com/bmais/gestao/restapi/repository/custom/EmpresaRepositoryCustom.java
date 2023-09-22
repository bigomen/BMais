package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Empresa;

import java.util.Collection;
import java.util.Optional;

public interface EmpresaRepositoryCustom {

    public Optional<Empresa> pesquisarEmpresa(Long id);

    Collection<Empresa> listarEmpresas();
}
