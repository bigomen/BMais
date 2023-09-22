package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusUsuarioMapper;
import com.bmais.gestao.restapi.model.StatusUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusUsuario extends RestModel<StatusUsuario> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusUsuario restParaModel() {
        return StatusUsuarioMapper.INSTANCE.convertToModel(this);
    }
}
