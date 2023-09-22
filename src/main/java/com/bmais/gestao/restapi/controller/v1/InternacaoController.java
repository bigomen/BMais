package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestInternacao;
import com.bmais.gestao.restapi.restmodel.RestInternacaoPesquisa;
import com.bmais.gestao.restapi.service.InternacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/internacoes/v1")
@Validated
public class InternacaoController {

    private final InternacaoService internacaoService;

    @Autowired
    public InternacaoController(InternacaoService internacaoService) {
        this.internacaoService = internacaoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestInternacao> lista(RestInternacaoPesquisa params){
        return internacaoService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_ativas")
    public Collection<RestInternacao> listaAtiva(RestInternacaoPesquisa params){
        return internacaoService.listaAtiva(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestInternacao detalhes(@PathVariable String id){
        return internacaoService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public RestInternacao novo(@RequestBody RestInternacao restInternacao){
        return internacaoService.novo(restInternacao);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestInternacao restInternacao, @PathVariable String id){
        internacaoService.atualizar(restInternacao, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
            internacaoService.apagar(id);
    }
}
