package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestCID;
import com.bmais.gestao.restapi.restmodel.RestCIDPesquisa;
import com.bmais.gestao.restapi.service.CIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/cid/v1")
@Validated
public class CIDController {

    private final CIDService cidService;

    @Autowired
    public CIDController(CIDService cidService) {
        super();
        this.cidService = cidService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/{pagina}")
    public Page<RestCID> listar(@PathVariable int pagina, RestCIDPesquisa params)
    {
        return cidService.listar(pagina, params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestCID detalhes(@PathVariable String id){
        return cidService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public RestCID novo(@Valid @RequestBody RestCID restCID)
    {
        return cidService.novo(restCID);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@Valid @RequestBody RestCID restCID, @PathVariable String id) {
        cidService.update(id, restCID);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        cidService.delete(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestCID> listaSimples(){
        return cidService.listaSimples();
    }
}
