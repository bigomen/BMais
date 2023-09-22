package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestProntuarioMotivoAlta;
import com.bmais.gestao.restapi.service.ProntuarioMotivoAltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/prontuario_motivo_alta/v1")
@Validated
public class ProntuarioMotivoAltaController {

    private final ProntuarioMotivoAltaService prontuarioMotivoAltaService;

    @Autowired
    public ProntuarioMotivoAltaController(ProntuarioMotivoAltaService prontuarioMotivoAltaService) {
        super();
        this.prontuarioMotivoAltaService = prontuarioMotivoAltaService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestProntuarioMotivoAlta> lista(){
        return prontuarioMotivoAltaService.lista();
    }
}
