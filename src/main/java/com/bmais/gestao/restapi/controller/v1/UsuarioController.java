package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestUsuario;
import com.bmais.gestao.restapi.restmodel.RestUsuarioPesquisa;
import com.bmais.gestao.restapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/usuarios/v1")
@Validated
public class UsuarioController  {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestUsuario> lista(RestUsuarioPesquisa params){
        return usuarioService.lista(params);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/detalhes/{id}")
    public RestUsuario detalhes(@PathVariable String id){
       return usuarioService.detalhes(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void novo(@RequestBody RestUsuario usuario) throws MessagingException {
        usuarioService.novo(usuario);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizar(@RequestBody RestUsuario restUsuario, @PathVariable String id){
        usuarioService.atualizar(restUsuario, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable String id){
        usuarioService.apagar(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/listaSimples")
    public Collection<RestUsuario> listaSimples(){
        return usuarioService.listaSimples();
    }
}