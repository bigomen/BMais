package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaGrupoUsuario
{
    AU("Auditor"),
    CO("Colaborador"),
    CL("Cliente");

    private String descricao;
}
