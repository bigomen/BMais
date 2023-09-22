package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import com.bmais.gestao.restapi.mapper.DadosBancariosMapper;
import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.enums.TipoContaBancaria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestDadosBancarios extends RestModel<DadosBancarios> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "banco")
    private RestBanco banco;

    @JsonProperty(value = "agencia")
    private String agencia;

    @JsonProperty(value = "conta")
    private String conta;

    @JsonProperty(value = "tipo_conta_bancaria")
    private TipoContaBancaria tipoContaBancaria;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @JsonProperty(value = "empresa")
    private RestEmpresa matriz;

    @JsonProperty(value = "proximoCheque")
    private Integer proximoCheque;

    @JsonProperty(value = "gerente")
    private String gerente;

    @JsonProperty(value = "limeteCredito")
    private BigDecimal limiteCredito;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "site")
    private String siteBanco;

    @JsonProperty(value = "saldo")
    private BigDecimal saldo;
    
    @JsonProperty(value = "ativo")
    private boolean ativo;

    @Override
    public DadosBancarios restParaModel() {
        return DadosBancariosMapper.INSTANCE.convertToModel(this);
    }
}
