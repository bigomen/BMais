package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestAcomodacao;
import com.bmais.gestao.restapi.service.AcomodacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
@RequestMapping("/acomodacao/v1")
public class AcomodacaoController
{
    private final AcomodacaoService acomodacaoService;

    @Autowired
    public AcomodacaoController(AcomodacaoService acomodacaoService)
    {
        this.acomodacaoService = acomodacaoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Collection<RestAcomodacao> listar()
    {
        return acomodacaoService.listar();
    }
}
