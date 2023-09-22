package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.controller.v1.doc.RamoAtividadeDoc;
import com.bmais.gestao.restapi.restmodel.RestRamoAtividade;
import com.bmais.gestao.restapi.service.RamoAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ramoAtividade/v1")
@Validated
public class RamoAtividadeController implements RamoAtividadeDoc {

    private final RamoAtividadeService ramoAtividadeService;

    @Autowired
    public RamoAtividadeController(RamoAtividadeService ramoAtividadeService) {
        super();
        this.ramoAtividadeService = ramoAtividadeService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("lista")
    public Collection<RestRamoAtividade> listar()
    {
        return ramoAtividadeService.listar();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void cadastrar(@RequestBody RestRamoAtividade restRamoAtividade){
        ramoAtividadeService.cadastrar(restRamoAtividade);
    }

}
