package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.model.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;

@Data
@NoArgsConstructor
public abstract class RestPessoaJuridica extends RestModel<PessoaJuridica> implements Serializable {

    private static final long serialVersionUID = 1L;

    //ADMINISTRATIVO
    @JsonProperty(value = "razao_social")
    protected String razaoSocial;

    @JsonProperty(value = "cnpj")
    protected String cnpj;

    @JsonProperty(value = "ie")
    protected String ie;

    @JsonProperty(value = "im")
    protected String im;

    @JsonProperty(value = "email")
    protected String email;

    @JsonProperty(value = "telefone")
    protected String telefone;

    @JsonProperty(value = "data_inicio")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    protected LocalDate dataInicio;

    @JsonProperty(value = "data_fim")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    protected LocalDate dataFim;

    @JsonProperty(value = "endereco")
    protected RestEndereco endereco;

    @JsonProperty(value = "status")
    protected RestStatusPessoaJuridica status;

    @JsonProperty(value = "dataInclusao")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    protected LocalDate dataInclusao;

    @JsonProperty(value = "documentos")
    protected Collection<RestDocumento> documentos;

    @JsonProperty(value = "contatos")
    private Collection<RestContato> contatos;
    
    //FINANCEIRO
    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "pis")
    private BigDecimal pis;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "ir")
    private BigDecimal ir;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "cofins")
    private BigDecimal cofins;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "csll")
    private BigDecimal csll;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "gps")
    private BigDecimal gps;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "iss")
    private BigDecimal iss;

    @Digits(integer = 9, fraction = 2, message = "Campos com frações é permitido apenas 2 decimais")
    @JsonProperty(value =  "outros_impostos")
    private BigDecimal outrosImpostos;
    
    @JsonProperty(value = "dados_bancarios")
    protected Collection<RestDadosBancarios> dadosBancarios;

    //OBSERVAÇOES
    @JsonProperty(value = "observacao")
    protected String observacao;
}
