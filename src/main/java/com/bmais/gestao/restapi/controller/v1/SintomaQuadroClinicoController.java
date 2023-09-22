package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestSintomaQuadroClinico;
import com.bmais.gestao.restapi.service.SintomaQuadroClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/sintoma_quadro_clinico/v1")
public class SintomaQuadroClinicoController {

    private final SintomaQuadroClinicoService sintomaQuadroClinicoService;

    @Autowired
    public SintomaQuadroClinicoController(SintomaQuadroClinicoService sintomaQuadroClinicoService) {
        super();
        this.sintomaQuadroClinicoService = sintomaQuadroClinicoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestSintomaQuadroClinico> lista(){
        return sintomaQuadroClinicoService.lista();
    }
}
