package com.bmais.gestao.restapi.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestRecuperacaoSenha {

    private String token;
    private String senha;
}
