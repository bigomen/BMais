package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.FolhaPagamentoColaboradorMapper;
import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestFolhaPagamentoColaborador extends RestModel<FolhaPagamentoColaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "colaborador")
    private RestColaborador colaborador;

    @JsonProperty(value = "tipo_pagamento")
    private RestTipoPagamentoColaborador tipoPagamento;

    @JsonProperty(value = "valor")
    private BigDecimal valor;

    @JsonProperty(value = "pagamento_gerado")
    private RestPagamentosGerados pagamentosGerados;

    @Override
    public FolhaPagamentoColaborador restParaModel(){
        return FolhaPagamentoColaboradorMapper.INSTANCE.convertToModel(this);
    }
}
