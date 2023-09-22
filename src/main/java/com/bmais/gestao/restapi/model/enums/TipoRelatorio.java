package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoRelatorio {

    VH("Visita Homecare"),
    VC("Visita Concorrente"),
    CI("Capeante Internacao"),
    CP("Capeante PS/Ambulatorio");

    private String tipo;
}
