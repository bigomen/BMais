package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomecarePesquisa;
import com.bmais.gestao.restapi.service.VisitaHomecareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/visitas_homecare/v1")
public class VisitaHomecareController {

    private final VisitaHomecareService visitaHomecareService;

    @Autowired
    public VisitaHomecareController(VisitaHomecareService visitaHomecareService) {
        super();
        this.visitaHomecareService = visitaHomecareService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestVisitaHomeCare> lista(RestVisitaHomecarePesquisa params){
        return visitaHomecareService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestVisitaHomeCare detalhes(@PathVariable String id){
        return visitaHomecareService.detalhes(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void novo(@RequestBody RestVisitaHomeCare restVisitaHomeCare){
            visitaHomecareService.novo(restVisitaHomeCare);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void atualizar(@RequestBody @Valid RestVisitaHomeCare restVisitaHomeCare, @PathVariable String id){
            visitaHomecareService.atualizar(restVisitaHomeCare, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/{id}")
    public void finalizar(@PathVariable String id)
    {
        visitaHomecareService.finalizar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        visitaHomecareService.apagar(id);
    }

//    @GetMapping("/detalhes_paciente/{id}")
//    public ResponseEntity detalhesPaciente(@PathVariable String id){
//        try {
//            return ResponseEntity.ok().body(visitaHomecareService.detalhesPaciente(id));
//        }catch (ObjectNotFoundException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
