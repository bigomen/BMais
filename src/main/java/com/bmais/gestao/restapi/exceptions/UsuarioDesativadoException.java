package com.bmais.gestao.restapi.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UsuarioDesativadoException extends AuthenticationException {
    public UsuarioDesativadoException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsuarioDesativadoException(String msg) {
        super(msg);
    }
}
