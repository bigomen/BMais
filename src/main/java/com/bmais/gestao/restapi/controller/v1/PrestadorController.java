package com.bmais.gestao.restapi.controller.v1;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.restmodel.RestPrestador;
import com.bmais.gestao.restapi.restmodel.RestPrestadorPesquisa;
import com.bmais.gestao.restapi.service.PrestadorService;

@RestController
@RequestMapping("/prestadores/v1")
@Validated
public class PrestadorController {

    private final PrestadorService prestadorService;

    @Autowired
    public PrestadorController(PrestadorService prestadorService) {
        super();
        this.prestadorService = prestadorService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestPrestador> listar(RestPrestadorPesquisa params){return prestadorService.listar(params);}


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestPrestador> listaSimples(){
        return prestadorService.listaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestPrestador detalhes(@Valid @PathVariable String id){return prestadorService.detalhes(id);}

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestPrestador restFornecedorAuditoria){
            prestadorService.novo(restFornecedorAuditoria);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@Valid @RequestBody RestPrestador restFornecedorAuditoria, @PathVariable String id){
        prestadorService.atualizar(restFornecedorAuditoria, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){prestadorService.apagar(id);}
}