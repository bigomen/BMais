package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorPesquisa;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorView;
import com.bmais.gestao.restapi.service.FolhaPagamentoColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/folha_pagamento/v1/colaborador")
public class FolhaPagamentoColaboradorController {

    private final FolhaPagamentoColaboradorService folhaPagamentoColaboradorService;

    @Autowired
    public FolhaPagamentoColaboradorController(FolhaPagamentoColaboradorService folhaPagamentoColaboradorService) {
        super();
        this.folhaPagamentoColaboradorService = folhaPagamentoColaboradorService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestFolhaPagamentoColaborador> lista(RestFolhaPagamentoColaboradorPesquisa params){
        return folhaPagamentoColaboradorService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestFolhaPagamentoColaboradorView detalhes(@PathVariable String id){
        return folhaPagamentoColaboradorService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestFolhaPagamentoColaboradorView folha){
        folhaPagamentoColaboradorService.novo(folha);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping
    public void atualizar(@RequestBody RestFolhaPagamentoColaboradorView folha){
        folhaPagamentoColaboradorService.atualizar(folha);
    }
}
