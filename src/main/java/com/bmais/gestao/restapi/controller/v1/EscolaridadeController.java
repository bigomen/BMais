package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestEscolaridade;
import com.bmais.gestao.restapi.service.EscolaridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/escolaridades/v1")
@Validated
public class EscolaridadeController {

    private final EscolaridadeService escolaridadeService;

    @Autowired
    public EscolaridadeController(EscolaridadeService escolaridadeService) {
        super();
        this.escolaridadeService = escolaridadeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Collection<RestEscolaridade> listar() {
        return escolaridadeService.listar();
    }
}
