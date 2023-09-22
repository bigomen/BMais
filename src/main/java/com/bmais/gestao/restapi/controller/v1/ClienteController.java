package com.bmais.gestao.restapi.controller.v1;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import com.bmais.gestao.restapi.restmodel.RestPessoaJuridicaPesquisa;
import com.bmais.gestao.restapi.service.ClienteService;

@RestController
@RequestMapping("/clientes/v1")
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        super();
        this.clienteService = clienteService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestCliente detalhes(@PathVariable String id)
    {
        return clienteService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestCliente> listar(RestPessoaJuridicaPesquisa params) {
        return clienteService.listar(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestCliente> listaSimples(){
        return clienteService.listaSimples();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@Valid @RequestBody RestCliente restCliente) {
            clienteService.novo(restCliente);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void alterar(@Valid @RequestBody RestCliente restCliente, @PathVariable String id) {
        clienteService.update(restCliente, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        clienteService.deletar(id);
    }
}