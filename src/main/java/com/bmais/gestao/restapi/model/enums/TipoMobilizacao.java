package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMobilizacao {

    R("Restrito ao Leito"),
    DC("Deambulando com ajuda"),
    DS("Deambulando sem ajuda");

    private String descricao;
}
