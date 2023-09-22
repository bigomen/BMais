package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.model.Tuss;
import com.bmais.gestao.restapi.restmodel.RestTuss;
import com.bmais.gestao.restapi.service.TussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/tuss/v1")
@Validated
public class TussController  {

    private final TussService tussService;

    @Autowired
    public TussController(TussService tussService) {
        super();
        this.tussService = tussService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestTuss detalhes(@PathVariable String id) {
        return tussService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/{pagina}")
    public Page<RestTuss> pesquisar(@RequestParam(value = "codigo", required = false) String codigo,
                                @RequestParam(value = "descricao", required = false) String descricao,
                                @RequestParam(value = "status", required = false) Long status,
                                @RequestParam(value = "orderBy") String orderBy,
                                @RequestParam(value = "pageSize") int pageSize,
                                @PathVariable int pagina)
    {
        return tussService.pesquisar(codigo, descricao, status, orderBy, pagina, pageSize);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestTuss restTuss)
    {
        tussService.novo(restTuss);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@PathVariable String id, @Valid @RequestBody RestTuss restTuss) {
        tussService.alterar(id, restTuss);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        tussService.deletar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/listaSimples")
    public Collection<RestTuss> listaSimples(){
        return tussService.listaSimples();
    }
}
