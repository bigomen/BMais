package com.bmais.gestao.restapi.controller.v1;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.restmodel.RestAgenda;
import com.bmais.gestao.restapi.restmodel.RestAgendaPesquisa;
import com.bmais.gestao.restapi.restmodel.RestTipoAgenda;
import com.bmais.gestao.restapi.service.AgendaService;

@RestController
@RequestMapping(value = "/agenda/v1")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        super();
        this.agendaService = agendaService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestAgenda> lista(RestAgendaPesquisa params){
        return agendaService.lista(params);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/tipo")
    public Collection<RestTipoAgenda> listaTiposDeAgenda()
    {
    	return agendaService.listaTiposAgenda();
    }
    
    @GetMapping("/valida/{id}/{data}")
    public ResponseEntity<Boolean> temAgendaAuditor(@PathVariable(name = "id") String auditor, @PathVariable @DateTimeFormat(pattern = Constantes.PATTERN_DATA) LocalDate data)
    {
    	Boolean temAgenda = agendaService.auditorTemAgenda(auditor, data);
    	return ResponseEntity.ok(temAgenda);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void novo(@RequestBody RestAgenda restAgenda){
    	agendaService.novo(restAgenda);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void atualizar(@PathVariable String id, @RequestBody RestAgenda restAgenda){
        agendaService.atualizar(id, restAgenda);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable String id)
    {
    	agendaService.apagar(id);
    }
}
