package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import com.bmais.gestao.restapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/empresas/v1")
@Validated
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService){
        super();
        this.empresaService = empresaService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestEmpresa> listar(){
        return empresaService.listar();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/sedes")
    public Collection<RestEmpresa> listarSedes(){return empresaService.listarSedes();}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/filiais/{id}")
    public Collection<RestEmpresa> listarFiliais(@PathVariable String id){return empresaService.listarFiliais(id);}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestEmpresa detalhes(@PathVariable String id){
        return empresaService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestEmpresa restEmpresa){
        empresaService.novo(restEmpresa);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar (@Valid @RequestBody RestEmpresa restEmpresa, @PathVariable String id){
        empresaService.atualizar(restEmpresa, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id){empresaService.deletar(id);}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestEmpresa> listaSimples(){
        return empresaService.listaSimples();
    }

}