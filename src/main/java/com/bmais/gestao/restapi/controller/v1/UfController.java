package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.model.UF;
import com.bmais.gestao.restapi.repository.custom.UfRepositoryCustom;
import com.bmais.gestao.restapi.restmodel.RestMunicipio;
import com.bmais.gestao.restapi.restmodel.RestUf;
import com.bmais.gestao.restapi.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/uf/v1")
@Validated
public class UfController {

    private final UfService ufService;


    @Autowired
    public UfController(UfService ufService){
        super();
        this.ufService = ufService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestUf> listar(){return ufService.listar();}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/{estadoId}")
    public Collection<RestMunicipio> listarCidades(@PathVariable String estadoId){
        return ufService.listarCidades(estadoId);
    }




}
