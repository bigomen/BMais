package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestDadosBancarios;
import com.bmais.gestao.restapi.service.DadosBancariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/dados_bancarios/v1")
public class DadosBancariosController extends ControleBancarioController
{
    private final DadosBancariosService dadosBancariosService;

    @Autowired
    public DadosBancariosController(DadosBancariosService dadosBancariosService)
    {
        super();
        this.dadosBancariosService = dadosBancariosService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestDadosBancarios> listar(
            @RequestParam(name = "banco", required = false) Long banco,
            @RequestParam(name = "agencia", required = false) String agencia,
            @RequestParam(name = "conta", required = false) String conta)
    {
        return dadosBancariosService.listar(banco, agencia, conta);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/{id}")
    public RestDadosBancarios detalhar(@PathVariable(name = "id") String id)
    {
        return dadosBancariosService.detalhar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista/empresa/{id}")
    public Collection<RestDadosBancarios> listarPorMatriz(@PathVariable(name = "id") String id)
    {
        return dadosBancariosService.pesquisarPorEmpresa(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@PathVariable(name = "id") String id, @RequestBody RestDadosBancarios restDadosBancarios)
    {
        dadosBancariosService.atualizar(id, restDadosBancarios);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestDadosBancarios restDadosBancarios)
    {
        dadosBancariosService.novo(restDadosBancarios);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/{id}")
    public void ativarDesativar(@PathVariable(name = "id") String id, @RequestParam(name = "status") boolean status)
    {
        dadosBancariosService.ativarDesativar(id, status);
    }
}
