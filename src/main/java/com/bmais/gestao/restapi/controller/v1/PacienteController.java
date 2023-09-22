package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestPaciente;
import com.bmais.gestao.restapi.restmodel.RestPacientePesquisa;
import com.bmais.gestao.restapi.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/pacientes/v1")
@Validated
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        super();
        this.pacienteService = pacienteService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestPaciente> lista(RestPacientePesquisa params){
        return pacienteService.lista(params);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista_internados")
    public Collection<RestPaciente> listaInternados(RestPacientePesquisa params){
        return pacienteService.listaInternados(params);
    }

    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestPaciente> listaSimples(){
        return pacienteService.listaSimples();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestPaciente detalhes(@PathVariable String id)
    {
        return pacienteService.detalhes(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detalhes_mesclagem/{id}")
    public RestPaciente detalhesMesclagem(@PathVariable String id){
        return pacienteService.detalhesMesclagem(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/mesclagem/{id}")
    public void mesclagem(@RequestBody Collection<RestPaciente> pacientes,@PathVariable String id){
        pacienteService.mesclar(pacientes, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public RestPaciente novo(@Valid @RequestBody RestPaciente restPaciente) {
        return pacienteService.novo(restPaciente);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@Valid @RequestBody RestPaciente restPaciente, @PathVariable String id) {
        pacienteService.alterar(restPaciente, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        pacienteService.deletar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/visita_capeante/lista_simples")
    public Collection<RestPaciente> listaPacientesVisitaConcorrente(){
        return pacienteService.listaPacientesVisitaConcorrente();
    }
}
