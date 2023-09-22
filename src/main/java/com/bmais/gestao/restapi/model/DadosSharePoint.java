package com.bmais.gestao.restapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Deprecated
public class DadosSharePoint {

    private final String email = "sharepoint_teste@luxfacta.com";
    private final String senha = "Luxfacta@150";
    private final String dominio = "luxfactati.sharepoint.com";
    private final String site = "/sites/B-Teste";
    private final String caminho = "Documentos/B+";
}
