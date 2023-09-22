package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EmpresaMapper;
import com.bmais.gestao.restapi.model.Empresa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestEmpresa extends RestModel<Empresa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "razao_social")
    private String razaoSocial;

    @JsonProperty(value = "cnpj")
    private String cnpj;

    @JsonProperty(value = "ie")
    private String ie;

    @JsonProperty(value = "im")
    private String im;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "data_inicio")
    private LocalDate data_inicio;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @JsonProperty(value = "sede")
    private Boolean sede;

    @JsonProperty(value = "data_fim")
    private LocalDate data_fim;

    @JsonProperty(value = "sede_id")
    private RestEmpresa empresa;

    @JsonProperty(value = "status")
    private RestStatusEmpresa status;

    @JsonProperty(value = "dados_bancarios")
    protected Collection<RestDadosBancarios> dadosBancarios;

    @JsonProperty(value = "observacao")
    private String observacao;


    @Override
    public Empresa restParaModel() {
        Empresa empresa = EmpresaMapper.INSTANCE.convertToModel(this);

        if(empresa.getSede())
        {
            empresa.setEmpresa(null);
        }

        if(empresa.getDadosBancarios() != null){
            empresa.getDadosBancarios().forEach(dados -> dados.setEmpresa(empresa));
        }
        return empresa;
    }
}
