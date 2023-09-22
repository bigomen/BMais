package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestFornecedor;
import com.bmais.gestao.restapi.restmodel.RestFornecedorPesquisa;
import com.bmais.gestao.restapi.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/fornecedores/v1")
@Validated
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService){
        super();
        this.fornecedorService = fornecedorService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestFornecedor> listar(RestFornecedorPesquisa params){return fornecedorService.listar(params);}

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestFornecedor detalhes(@Valid @PathVariable String id){
        return fornecedorService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestFornecedor restFornecedor){
        fornecedorService.novo(restFornecedor);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@Valid @RequestBody RestFornecedor restFornecedor, @PathVariable String id){
        fornecedorService.atualizar(restFornecedor, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        fornecedorService.apagar(id);
    }

}
