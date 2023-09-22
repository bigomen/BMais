package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusFilialMapper;
import com.bmais.gestao.restapi.model.StatusFilial;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusFilial extends RestModel<StatusFilial> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusFilial restParaModel() {
        return StatusFilialMapper.INSTANCE.convertToModel(this);
    }
}
