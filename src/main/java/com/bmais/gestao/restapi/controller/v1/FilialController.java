//package com.bmais.gestao.restapi.controller.v1;
//
//import com.bmais.gestao.restapi.restmodel.RestFilial;
//import com.bmais.gestao.restapi.service.FilialService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.Collection;
//
//@RestController
//@RequestMapping("/filiais/v1")
//@Validated
//public class FilialController {
//
//    private final FilialService filialService;
//
//    @Autowired
//    public FilialController(FilialService filialService) {
//        super();
//        this.filialService = filialService;
//    }
//
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/{id}")
//    public RestFilial detalhes(@PathVariable String id) {
//        return filialService.detalhes(id);
//    }
//
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/lista")
//    public Collection<RestFilial> pesquisar()
//    {
//        return filialService.pesquisar();
//    }
//
//    @ResponseStatus(value = HttpStatus.CREATED)
//    @PostMapping
//    public RestFilial novo(@Valid @RequestBody RestFilial restFilial) {
//        return filialService.novo(restFilial);
//    }
//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PutMapping("/{id}")
//    public void alterar(@Valid @RequestBody RestFilial restFilial, @PathVariable String id) {
//        filialService.alterar(restFilial, id);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void deletar(@PathVariable String id) {
//        filialService.deletar(id);
//    }
//}