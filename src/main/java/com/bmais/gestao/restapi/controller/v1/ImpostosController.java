package com.bmais.gestao.restapi.controller.v1;


import com.bmais.gestao.restapi.restmodel.RestImpostos;
import com.bmais.gestao.restapi.service.ImpostosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/impostos/v1")
@Validated
public class ImpostosController {

    private final ImpostosService impostosService;

    @Autowired
    public ImpostosController(ImpostosService impostosService) {
        this.impostosService = impostosService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/valores")
    public Collection<RestImpostos> valores(){
        return impostosService.valores();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/valores/atualizar")
    public void atualizar(@Valid @RequestBody Collection<RestImpostos> restImpostos){
            impostosService.atualizar(restImpostos);
    }
}
