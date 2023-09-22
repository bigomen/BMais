//package com.bmais.gestao.restapi.restmodel;
//
//import com.bmais.gestao.restapi.mapper.FilialMapper;
//import com.bmais.gestao.restapi.model.Filial;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Collection;
//
//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
//public class RestFilial extends RestModel<Filial> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @JsonProperty(value = "razao_social")
//    private String razaoSocial;
//
//    @JsonProperty(value = "cnpj")
//    private String cnpj;
//
//    @JsonProperty(value = "ie")
//    private String ie;
//
//    @JsonProperty(value = "im")
//    private String im;
//
//    @JsonProperty(value = "email")
//    private String email;
//
//    @JsonProperty(value = "telefone")
//    private String telefone;
//
//    @JsonProperty(value = "cor")
//    private String cor;
//
//    @JsonProperty(value = "data_inicio")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    protected LocalDateTime dataInicio;
//
//    @JsonProperty(value = "data_fim")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    protected LocalDateTime dataFim;
//
//    @JsonProperty(value = "endereco")
//    private RestEndereco endereco;
//
//    @JsonProperty(value = "empresa")
//    private RestEmpresa empresa;
//
//    @JsonProperty(value = "status")
//    private RestStatusFilial status;
//
//    @Override
//    public Filial restParaModel() {
//        return FilialMapper.INSTANCE.convertToModel(this);
//    }
//}
