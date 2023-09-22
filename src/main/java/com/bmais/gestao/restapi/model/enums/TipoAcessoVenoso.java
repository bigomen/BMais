package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAcessoVenoso {

    C("Central"),
    I("Itermitente"),
    P("Periférico");

    private String descricao;
}
