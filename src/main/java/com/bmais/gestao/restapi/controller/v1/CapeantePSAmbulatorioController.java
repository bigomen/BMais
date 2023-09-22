package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.restmodel.RestCapeantePesquisa;
import com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/capeantes/ps_ambulatorio/v1")
public class CapeantePSAmbulatorioController {

    private final CapeantePSAmbulatorioService capeantePSAmbulatorioService;

    public CapeantePSAmbulatorioController(CapeantePSAmbulatorioService capeantePSAmbulatorioService) {
        this.capeantePSAmbulatorioService = capeantePSAmbulatorioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestCapeantePSAmbulatorio> lista(RestCapeantePesquisa params){
        return capeantePSAmbulatorioService.listar(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestCapeantePSAmbulatorio detalhes(@PathVariable String id){
        return capeantePSAmbulatorioService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestCapeantePSAmbulatorio restCapeantePSAmbulatorio){
        capeantePSAmbulatorioService.novo(restCapeantePSAmbulatorio);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/rascunho")
    public void novoRescunho(@RequestBody RestCapeantePSAmbulatorio restCapeantePSAmbulatorio){
        capeantePSAmbulatorioService.novoRascunho(restCapeantePSAmbulatorio);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestCapeantePSAmbulatorio restCapeantePSAmbulatorio, @PathVariable String id){
        capeantePSAmbulatorioService.atualizar(restCapeantePSAmbulatorio, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/aprovar/{id}")
    public void aprovar(@PathVariable String id){
        capeantePSAmbulatorioService.aprovar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/reprovar/{id}")
    public void reprovar(@PathVariable String id){
        capeantePSAmbulatorioService.reprovar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("{id}")
    public void apagar(@PathVariable String id){
            capeantePSAmbulatorioService.deletar(id);
    }
}