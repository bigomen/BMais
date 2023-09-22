package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EmergenciaMapper;
import com.bmais.gestao.restapi.model.Emergencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestEmergencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "parentesco")
    private String parentesco;

    @JsonProperty(value = "endereco")
    private String endereco;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "celular")
    private String celular;

    @JsonProperty(value = "telefone_comercial")
    private String telefoneComercial;

    public Emergencia restParaModel() {
        return EmergenciaMapper.INSTANCE.convertToModel(this);
    }
}
