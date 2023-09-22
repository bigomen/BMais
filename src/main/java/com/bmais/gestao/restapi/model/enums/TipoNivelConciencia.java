package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoNivelConciencia {

    L("Lucido"),
    C("Confuso"),
    T("Torporoso"),
    CO("Comatoso");

    private String descricao;
}
