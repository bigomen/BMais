package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTraquesostomia {

    F("Flexivel"),
    M("Metálica");

    private String descricao;
}
