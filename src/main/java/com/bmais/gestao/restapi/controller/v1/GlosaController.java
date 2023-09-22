package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.model.enums.Responsabilidade;
import com.bmais.gestao.restapi.restmodel.RestGlosa;
import com.bmais.gestao.restapi.service.GlosaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/tipos_glosas/v1")
@Validated
public class GlosaController {

    private final GlosaService glosaService;

    @Autowired
    public GlosaController(GlosaService glosaService) {
        super();
        this.glosaService = glosaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Collection<RestGlosa> listar(@RequestParam(value = "codigo", required = false) String codigo,
                                             @RequestParam(value = "responsabilidade", required = false) Responsabilidade responsabilidade) {
        return glosaService.listar(codigo, responsabilidade);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RestGlosa detalhes(@PathVariable String id) {
        return glosaService.detalhes(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public RestGlosa inserir(@Valid @RequestBody RestGlosa restGlosa) {
        return glosaService.inserir(restGlosa);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@Valid @RequestBody RestGlosa restGlosa, @PathVariable String id) {
        glosaService.alterar(restGlosa, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        glosaService.deletar(id);
    }
}
