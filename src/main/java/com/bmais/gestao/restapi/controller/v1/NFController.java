package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestNF;
import com.bmais.gestao.restapi.restmodel.RestNFPesquisa;
import com.bmais.gestao.restapi.service.NFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("nf/v1")
@Validated
public class NFController {

    private final NFService nfService;

    @Autowired
    public NFController(NFService nfService) {
        super();
        this.nfService = nfService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestNF> listar(RestNFPesquisa params){
        return nfService.listar(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestNF detalhes(@Valid @PathVariable String id){
        return nfService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public RestNF novo(@Valid @RequestBody RestNF restNF){
        return nfService.novo(restNF);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public RestNF atualizar(@Valid @RequestBody RestNF restNF ,@PathVariable String id){
        return nfService.atualizar(restNF, id);
    }
}
