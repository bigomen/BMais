//package com.bmais.gestao.restapi.controller.v1;
//
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.service.HistoricoCapeanteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.Collection;
//
//@RestController
//@RequestMapping("/historico_capeante/v1")
//@Validated
//public class HistoricoCapeanteController {
//
//    private final HistoricoCapeanteService historicoCapeanteService;
//
//    @Autowired
//    public HistoricoCapeanteController(HistoricoCapeanteService historicoCapeanteService) {
//        super();
//        this.historicoCapeanteService = historicoCapeanteService;
//    }
//
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/listar/{idCapeante}")
//    public Collection<RestHistoricoCapeante> listar(@PathVariable String idCapeante) {
//        return historicoCapeanteService.listar(idCapeante);
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public RestHistoricoCapeante novo(@Valid @RequestBody RestHistoricoCapeante restHistoricoCapeante) {
//        return historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//}
