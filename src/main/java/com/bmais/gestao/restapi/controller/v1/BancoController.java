package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestBanco;
import com.bmais.gestao.restapi.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/bancos/v1")
@Validated
public class BancoController {

    private final BancoService bancoService;

    @Autowired
    public BancoController(BancoService bancoService) {
        super();
        this.bancoService = bancoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestBanco> listar()
    {
        return bancoService.listar();
    }
}
