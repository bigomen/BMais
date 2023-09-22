package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.restmodel.RestBeneficio;
import com.bmais.gestao.restapi.service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/beneficios/v1")
public class BeneficioController {

    private final BeneficioService beneficioService;

    @Autowired
    public BeneficioController(BeneficioService beneficioService) {
        super();
        this.beneficioService = beneficioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestBeneficio> lista(){
        return beneficioService.lista();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestBeneficio> listaSimples(){
        return beneficioService.listaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestBeneficio restBeneficio){
        beneficioService.novo(restBeneficio);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestBeneficio restBeneficio, @PathVariable String id){
        beneficioService.atualizar(restBeneficio, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        beneficioService.apagar(id);
    }
}
