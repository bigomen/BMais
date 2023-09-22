package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusGlosaMapper;
import com.bmais.gestao.restapi.model.StatusGlosa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusGlosa extends RestModel<StatusGlosa> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusGlosa restParaModel() {
        return StatusGlosaMapper.INSTANCE.convertToModel(this);
    }
}
