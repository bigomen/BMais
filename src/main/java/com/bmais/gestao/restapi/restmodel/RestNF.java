package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.NFMapper;
import com.bmais.gestao.restapi.model.NF;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestNF extends RestModel<NF> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "empresa")
    private RestEmpresa empresa;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "data_emissao")
    private LocalDate dataEmissao;

    @JsonProperty(value = "data_vencimento")
    private LocalDate dataVencimento;

    @JsonProperty(value = "servico")
    private String servico;

    @JsonProperty(value = "numero_nota")
    private String numeroNota;

    @JsonProperty(value = "status")
    private RestStatusNF status;

    @JsonProperty(value = "dados_bancarios")
    private RestDadosBancarios dadosBancarios;

    @JsonProperty(value = "natureza")
    private String natureza;

    @JsonProperty(value = "fatura")
    private String fatura;

    @JsonProperty(value = "mensagem")
    private String mensagem;

    @JsonProperty(value = "acrescimo")
    private BigDecimal acrescimo;

    @JsonProperty(value = "desconto")
    private BigDecimal desconto;

    @JsonProperty(value = "data_pagamento")
    private LocalDate dataPagamento;

    @JsonProperty(value = "items")
    private Collection<RestNFItem> nfItems;

    @Override
    public NF restParaModel(){
        NF nf =  NFMapper.INSTANCE.convertToModel(this);
        if(nf.getNfItems() != null){
            nf.getNfItems().forEach(n -> n.setNf(nf));
        }
        return nf;
    }
}
