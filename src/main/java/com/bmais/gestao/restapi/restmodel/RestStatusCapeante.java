package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusCapeanteMapper;
import com.bmais.gestao.restapi.model.StatusCapeante;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusCapeante extends RestModel<StatusCapeante> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;



    @Override
    public StatusCapeante restParaModel() {
        return StatusCapeanteMapper.INSTANCE.convertToModel(this);
    }
}
