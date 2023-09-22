package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.PagamentosGeradosMapper;
import com.bmais.gestao.restapi.model.PagamentosGerados;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestPagamentosGerados extends RestModel<PagamentosGerados> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "lancamento")
    private Long lancamento;

    @JsonProperty(value = "movimentacao_bancaria")
    private Long movimentacaoBancaria;

    @JsonProperty(value = "data_emissao")
    private LocalDate dataEmissao;

    @JsonProperty(value = "data_vencimento")
    private LocalDate dataVencimento;

    @JsonProperty(value = "valor")
    private BigDecimal valor;

    @JsonProperty(value = "documento")
    private String documento;

    @JsonProperty(value = "data_pagamento")
    private LocalDate dataPagamento;

    @JsonProperty(value = "valor_pago")
    private BigDecimal valorPago;

    @JsonProperty(value = "historico")
    private String historico;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "status")
    private RestStatusPagamentosGerados status;

    @JsonProperty(value = "favorecido")
    private String favorecido;

    @JsonProperty(value = "empresa")
    private RestEmpresa empresa;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @Override
    public PagamentosGerados restParaModel(){
        return PagamentosGeradosMapper.INSTANCE.convertToModel(this);
    }
}
