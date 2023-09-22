package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.restmodel.RestHospitalCliente;
import com.bmais.gestao.restapi.restmodel.RestHospitalPesquisa;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import com.bmais.gestao.restapi.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/hospitais/v1")
@Validated
public class HospitalController{

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        super();
        this.hospitalService = hospitalService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestHospital> listar(RestHospitalPesquisa params)
    {
        return hospitalService.listar(params);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/cliente/{cliente}")
    public Collection<RestHospitalCliente>pesquisarHospitaisPorCliente(@PathVariable String cliente)
    {
    	return hospitalService.listar(cliente);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/hospitaisVisitaConcorrente/{cliente}")
    public Collection<RestHospitalCliente> hospitaisVisitaConcorrente(@PathVariable String cliente)
    {
        return hospitalService.hospitaisVisitaConcorrente(cliente, Servico.VISITA_CONCORRENTE);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/hospitaisCapeanteInternacao/{cliente}")
    public Collection<RestHospitalCliente> hospitaisCapeanteInternacao(@PathVariable String cliente)
    {
        return hospitalService.hospitaisVisitaConcorrente(cliente, Servico.CAPEANTE_INTERNACAO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/hospitaisCapeantePS/{cliente}")
    public Collection<RestHospitalCliente> hospitaisCapeantePS(@PathVariable String cliente)
    {
        return hospitalService.hospitaisVisitaConcorrente(cliente, Servico.CAPEANTE_PS);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/hospitaisVisitaHomecare/{cliente}")
    public Collection<RestHospitalCliente> hospitaisVisitaHomecare(@PathVariable String cliente)
    {
        return hospitalService.hospitaisVisitaConcorrente(cliente, Servico.VISITA_HOMECARE);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestHospital detalhes(@Valid @PathVariable String id){
        return hospitalService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestHospital restHospital){
        hospitalService.novo(restHospital);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@Valid @RequestBody RestHospital restHospital, @PathVariable String id){
        hospitalService.atualizar(restHospital, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        hospitalService.apagar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestHospital> listaSimples(){
        return hospitalService.listaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/vinculos/{id}")
    public Collection<RestVinculo> vinculos(@PathVariable String id){
        return hospitalService.vinculos(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/visita_capeante/lista_simples")
    public Collection<RestHospital> listaHospitaisVisitaConcorrente(){
        return hospitalService.listaHospitaisVisitaConcorrente();
    }
}