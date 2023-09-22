package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.restmodel.RestServico;
import com.bmais.gestao.restapi.restmodel.RestServicoPesquisa;
import com.bmais.gestao.restapi.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/servicos/v1")
@Validated
public class ServicoController {

    public final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        super();
        this.servicoService = servicoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestServico> listar(RestServicoPesquisa params){return servicoService.listar(params);}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestServico> listaSimples(){
        return servicoService.listaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestServico detalhes(@PathVariable String id){
        return servicoService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestServico restServico){
        servicoService.novo(restServico);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@Valid @RequestBody RestServico restServico, @PathVariable String id){
        servicoService.atualizar(restServico, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        servicoService.apagar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/cliente/listaSimples/{id}")
    public Collection<RestServico> clienteListaSimples(@PathVariable String id){
        return servicoService.clienteListaSimples(id);
    }
}
