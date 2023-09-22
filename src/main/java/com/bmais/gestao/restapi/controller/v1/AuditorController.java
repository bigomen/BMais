package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.restmodel.RestAuditor;
import com.bmais.gestao.restapi.restmodel.RestTipoAuditor;
import com.bmais.gestao.restapi.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/auditores/v1")
public class AuditorController {

    private final AuditorService auditorService;

    @Autowired
    public AuditorController(AuditorService auditorService) {
        this.auditorService = auditorService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestAuditor> lista(){
        return auditorService.lista();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/medicos/lista_simples")
    public Collection<RestAuditor> medicosListaSimples()
    {
        return auditorService.medicosListaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/enfermeiros/lista_simples")
    public Collection<RestAuditor> enfermeirosListaSimples()
    {
        return auditorService.enfermeirosListaSimples();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/hospital/{id}")
    public Map<String, List<RestAuditor>> auditoresPorHospital(@PathVariable(name = "id") String hospital)
    {
    	return auditorService.auditoresPorHospital(hospital);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestAuditor detalhar(@PathVariable(name = "id") String id)
    {
        return auditorService.detalhar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestAuditor restAuditor) throws MessagingException {
        auditorService.novo(restAuditor);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@PathVariable(name = "id") String id, @Valid @RequestBody RestAuditor restAuditor)
    {
        auditorService.alterar(id, restAuditor);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable(name = "id") String id)
    {
        auditorService.apagar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/tipos")
    public Collection<RestTipoAuditor> tiposDeAuditores()
    {
        return auditorService.listaTiposAuditores();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/visita_capeante/lista_simples/{idInternacao}")
    public Collection<RestAuditor> listaAuditoresVisitaConcorrente(@PathVariable String idInternacao){
        return auditorService.listaAuditoresVisitaConcorrente(idInternacao);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/visita_concorrente/{internacaoId}")
    public Collection<RestAuditor> auditoresVisitaConcorrente(@PathVariable String internacaoId){
        return auditorService.auditoresVisitaConcorrente(internacaoId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/visita_homecare/{prontuarioId}")
    public Collection<RestAuditor> auditoresVisitaHomeCare(@PathVariable String prontuarioId){
        return auditorService.auditoresVisitaHomeCare(prontuarioId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/capeante_internacao/{prontuarioId}")
    public Map<String, List<RestAuditor>> auditoresCapeanteInternacao(@PathVariable String prontuarioId){
        return auditorService.auditoresCapeanteInternacao(prontuarioId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/capeante_ps/{clienteId}/{hospitalId}")
    public Map<String, List<RestAuditor>> auditoresCapeanteInternacao(@PathVariable String clienteId, @PathVariable String hospitalId){
        return auditorService.auditoresCapeantePS(clienteId, hospitalId);
    }
}
