package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoComplexidade {

    B("Baixa"),
    M("Média"),
    A("Alta");

    private String descricao;
}
