package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.model.Logradouro;
import com.bmais.gestao.restapi.repository.custom.LogradouroRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logradouro/v1")
public class LogradouroController {


    @Autowired
    private LogradouroRepositoryCustom logradouroRepositoryCustom;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public List<Logradouro> listar(){return logradouroRepositoryCustom.listar();}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{logradouroId}")
    public Logradouro buscar(@PathVariable Long logradouroId) { return logradouroRepositoryCustom.buscar(logradouroId);}
}
