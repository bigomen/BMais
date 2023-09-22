package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusPacienteMapper;
import com.bmais.gestao.restapi.model.StatusPaciente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusPaciente extends RestModel<StatusPaciente> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusPaciente restParaModel() {
        return StatusPacienteMapper.INSTANCE.convertToModel(this);
    }
}
