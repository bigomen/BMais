package com.bmais.gestao.restapi.restmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class RestFolhaPagamentoColaboradorView {

    @JsonProperty(value = "folha_pagamento")
    private RestFolhaPagamento folhaPagamento;

    @JsonProperty(value = "folhas_colaborador")
    private Collection<RestFolhaPagamentoColaborador> folhaColaborador;
}
