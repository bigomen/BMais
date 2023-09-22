package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.*;
import com.bmais.gestao.restapi.service.VisitaConcorrenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/visita_concorrente/v1")
public class VisitaConcorrenteController {

    private final VisitaConcorrenteService visitaConcorrenteService;

    public VisitaConcorrenteController(VisitaConcorrenteService visitaConcorrenteService) {
        super();
        this.visitaConcorrenteService = visitaConcorrenteService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestVisita> lista(RestVisitaConcorrentePesquisa params){
        return visitaConcorrenteService.lista(params);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/avaliacao/itens")
    public Collection<RestAvaliacaoRelatorio> listaItensAvaliacao()
    {
    	return visitaConcorrenteService.listarItensAvaliacaoRelatorio();
    }
    
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @GetMapping("/altoCusto/componentes")
    public Collection<RestTipoComponente> listaComponentesAltoCusto()
    {
    	return visitaConcorrenteService.listarTiposComponentes();
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/situacao/lista")
    public Collection<RestStatusVisita> listaStatusVisita()
    {
    	return visitaConcorrenteService.listaStatus();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestVisita detalhes(@PathVariable String id){
        return visitaConcorrenteService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/evolucoes/{id}")
    public Collection<RestEvolucaoVisita> pesquisarEvolucoes(@PathVariable String id)
    {
    	return visitaConcorrenteService.pesquisarEvolucoes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/cid/paciente/{id}")
    public Collection<RestCID> pesquisarUltimoCID(@PathVariable(name = "id") String paciente)
    {
    	return visitaConcorrenteService.pesquisarUltimoCid(paciente);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestVisita restVisita){
        visitaConcorrenteService.novo(restVisita);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestVisita restVisita, @PathVariable String id){
        visitaConcorrenteService.atualizar(restVisita, id);
    }
    
    @PatchMapping("/aprovar/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void aprovar(@PathVariable(name = "id") String id)
    {
    	visitaConcorrenteService.aprovar(id);
    }
    
    @PatchMapping("/recusar/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void recusar(@PathVariable(name = "id") String id)
    {
    	visitaConcorrenteService.recusar(id);
    }
    
    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable(name = "id") String id)
    {
    	visitaConcorrenteService.excluir(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/acomodacoes")
    public Collection<RestAcomodacao> acomodacoes(){
        return visitaConcorrenteService.acomodacoes();
    }
}
