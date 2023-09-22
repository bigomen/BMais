package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestPagamento;
import com.bmais.gestao.restapi.service.ExtratoConciliadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/extrato/v1")
public class ExtratoConciliadoController extends ControleBancarioController
{
    private final ExtratoConciliadoService extratoConciliadoService;

    @Autowired
    public ExtratoConciliadoController(ExtratoConciliadoService extratoConciliadoService)
    {
        this.extratoConciliadoService = extratoConciliadoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/nao-conciliado")
    public Collection<RestPagamento> pesquisaExtratoNaoConciliado(@RequestParam(name = "empresa", required = false) String empresa,
                                                                  @RequestParam(name = "dadoBancario", required = false) String dadoBancario)
    {
        return extratoConciliadoService.pesquisarExtratosNaoConsolidados(empresa, dadoBancario);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/conciliado")
    public void pesquisaExtratoConciliado(@RequestParam(name = "empresa", required = false) String empresa,
                                          @RequestParam(name = "dadoBancario", required = false) String dadoBancario)
    {

    }
}
