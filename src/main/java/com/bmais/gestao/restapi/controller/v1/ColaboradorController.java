package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.restmodel.*;
import com.bmais.gestao.restapi.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/colaboradores/v1")
@Validated
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorController(ColaboradorService colaboradorService) {
        super();
        this.colaboradorService = colaboradorService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestColaborador> lista(RestColaboradorPesquisa params){
        return colaboradorService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestColaborador detalhes(@PathVariable String id){
        return colaboradorService.detalhes(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista/empresa/{id}")
    public Collection<RestColaborador> pesquisarColaboradores(@PathVariable(name = "id") String id)
    {
        return colaboradorService.pesquisarColaboradoresPorEmpresa(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody @Valid RestColaborador restColaborador){
        colaboradorService.novo(restColaborador);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody @Valid RestColaborador restColaborador, @PathVariable String id){
        colaboradorService.atualizar(restColaborador, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        colaboradorService.apagar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/administrativo")
    public Collection<RestColaborador> listaAdministrativo(){
        return colaboradorService.listaAdministrativo();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/estadoCivil")
    public Collection<RestEstadoCivil> listaEstadoCivil(){
        return colaboradorService.listaEstadoCivil();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/cargo/lista")
    public Collection<RestCargo> listaCargos(){
        return colaboradorService.listaCargos();
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/cargo")
    public Collection<RestCargo> novoCargo(@RequestBody RestCargo cargo){
        return colaboradorService.novoCargo(cargo);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/motivoDemissao")
    public Collection<RestMotivoDemissao> listaMotivos(){
        return colaboradorService.listaMotivos();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/tipoValeTransporte")
    public Collection<RestTipoValeTransporte> listaTipoValeTransporte(){
        return colaboradorService.listaTipoValeTransporte();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/tipoAfastamento")
    public Collection<RestTipoAfastamento> listaTipoAfastamento(){
        return colaboradorService.listaTipoAfastamento();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/status")
    public Collection<RestStatusColaborador> listaStatusColaborador(){
        return colaboradorService.listaStatusColaborador();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/listaSimples")
    public Collection<RestColaborador> listaSimples(){
        return colaboradorService.listaSimples();
    }
}
