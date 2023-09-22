package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCarePesquisa;
import com.bmais.gestao.restapi.service.ProntuarioVisitaHomeCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/prontuarios/v1") 
@Validated
public class ProntuarioVisitaHomeCareController {

    private final ProntuarioVisitaHomeCareService prontuarioService;

    @Autowired
    public ProntuarioVisitaHomeCareController(ProntuarioVisitaHomeCareService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestProntuarioVisitaHomeCare> lista(RestProntuarioVisitaHomeCarePesquisa params){
        return prontuarioService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_ativas")
    public Collection<RestProntuarioVisitaHomeCare> listaAtiva(RestProntuarioVisitaHomeCarePesquisa params){
        return prontuarioService.listaAtiva(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestProntuarioVisitaHomeCare detalhes(@PathVariable String id){
        return prontuarioService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCare){
        prontuarioService.novo(restProntuarioVisitaHomeCare);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCare, @PathVariable String id){
        prontuarioService.atualizar(restProntuarioVisitaHomeCare, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        prontuarioService.apagar(id);
    }
}
