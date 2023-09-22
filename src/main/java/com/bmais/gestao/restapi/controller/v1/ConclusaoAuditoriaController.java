package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestConclusaoAuditoriaHC;
import com.bmais.gestao.restapi.service.ConclusaoAuditoriaHCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/conclusao_auditoria/v1")
public class ConclusaoAuditoriaController {

    private final ConclusaoAuditoriaHCService conclusaoAuditoriaHCService;

    @Autowired
    public ConclusaoAuditoriaController(ConclusaoAuditoriaHCService conclusaoAuditoriaHCService) {
        super();
        this.conclusaoAuditoriaHCService = conclusaoAuditoriaHCService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestConclusaoAuditoriaHC> lista(){
        return conclusaoAuditoriaHCService.lista();
    }
}
