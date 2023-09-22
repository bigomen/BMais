package com.bmais.gestao.restapi.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoListaAuditor {

    Prod("produtividade");

    private String tipo;
}
