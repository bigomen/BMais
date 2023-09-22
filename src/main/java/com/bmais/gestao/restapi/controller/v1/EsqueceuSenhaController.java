package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestRecuperacaoSenha;
import com.bmais.gestao.restapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/esqueceuSenha/v1")
@Validated
public class EsqueceuSenhaController {

    private final UsuarioService usuarioService;

    @Autowired
    public EsqueceuSenhaController(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/recuperar/{email}")
    public void recuperacao(@Valid @PathVariable String email) throws MessagingException {
        usuarioService.updateResetPasswordToken(email);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/recuperar")
    public void recuperar(@Valid @RequestBody RestRecuperacaoSenha restRecuperacaoSenha){
        usuarioService.recuperar(restRecuperacaoSenha);
    }
}
