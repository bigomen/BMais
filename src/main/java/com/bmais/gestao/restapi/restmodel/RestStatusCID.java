package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusCIDMapper;
import com.bmais.gestao.restapi.model.StatusCID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusCID extends RestModel<StatusCID> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusCID restParaModel() {
        return StatusCIDMapper.INSTANCE.convertToModel(this);
    }
}
