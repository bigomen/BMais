package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestTipoAcessoUsuario;
import com.bmais.gestao.restapi.service.TipoAcessoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/tipoAcessoUsuario/v1")
public class TipoAcessoUsuarioController {

    private final TipoAcessoUsuarioService tipoAcessoUsuarioService;

    @Autowired
    public TipoAcessoUsuarioController(TipoAcessoUsuarioService tipoAcessoUsuarioService) {
        super();
        this.tipoAcessoUsuarioService = tipoAcessoUsuarioService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestTipoAcessoUsuario> listar()
    {
        return tipoAcessoUsuarioService.listar();
    }
}
