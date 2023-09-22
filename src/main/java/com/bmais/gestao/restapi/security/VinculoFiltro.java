package com.bmais.gestao.restapi.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VinculoFiltro {

    private Long hospitalId;
    private Long clienteId;
    private Long servicoId;
}
