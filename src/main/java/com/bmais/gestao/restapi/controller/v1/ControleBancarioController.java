package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import com.bmais.gestao.restapi.service.DadosBancariosService;
import com.bmais.gestao.restapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Collection;

public abstract class ControleBancarioController
{
    protected EmpresaService empresaService;

    @Autowired
    private void setEmpresaService(EmpresaService empresaService)
    {
        this.empresaService = empresaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/lista/dadosBancarios")
    public Collection<RestEmpresa> pesquisarEmpresas()
    {
        return empresaService.pesquisarEmpresasDadosBancarios();
    }
}
