package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTamanho {

    P("Pequeno"),
    M("Médio"),
    G("Grande");

    private String descricao;
}
