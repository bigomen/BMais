package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeante;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeantePesquisa;
import com.bmais.gestao.restapi.service.ProntuarioCapeanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/capeantes/prontuario/v1")
@Validated
public class ProntuarioCapeanteController {

    private final ProntuarioCapeanteService capeanteInternacaoService;

    @Autowired
    public ProntuarioCapeanteController(ProntuarioCapeanteService capeanteInternacaoService) {
        super();
        this.capeanteInternacaoService = capeanteInternacaoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestProntuarioCapeante> listar(RestProntuarioCapeantePesquisa params){
        return capeanteInternacaoService.listar(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("detalhes/{id}")
    public RestProntuarioCapeante detalhes(@PathVariable String id){
        return capeanteInternacaoService.detalhes(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public HashMap<String, String> novo(@RequestBody RestProntuarioCapeante restCapeanteInternacao){
        return capeanteInternacaoService.novo(restCapeanteInternacao);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestProntuarioCapeante restCapeanteInternacao, @PathVariable String id){
        capeanteInternacaoService.atualizar(restCapeanteInternacao, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
            capeanteInternacaoService.apagar(id);
    }
}
