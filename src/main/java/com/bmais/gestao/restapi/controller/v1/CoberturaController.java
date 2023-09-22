package com.bmais.gestao.restapi.controller.v1;

import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.restmodel.RestCobertura;
import com.bmais.gestao.restapi.restmodel.RestEscala;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;
import com.bmais.gestao.restapi.service.CoberturaService;

@RestController
@RequestMapping("/cobertura/v1")
public class CoberturaController {

    private final CoberturaService coberturaService;

    public CoberturaController(CoberturaService coberturaService) {
        super();
        this.coberturaService = coberturaService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestEscala> lista(RestEscalaPesquisa params)
    {
        return coberturaService.listaCoberturas(params);
    }
 
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void novo(@RequestBody Collection<RestCobertura> restCoberturas)
    {
    	coberturaService.novo(restCoberturas);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/escalas")
    public  Collection<RestEscala> listaEscalas(RestEscalaPesquisa params)
    {
        return coberturaService.listaEscala(params);
    }
}
