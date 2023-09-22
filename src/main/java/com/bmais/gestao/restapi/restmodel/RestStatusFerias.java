package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusFeriasMapper;
import com.bmais.gestao.restapi.model.StatusFerias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestStatusFerias extends RestModel<StatusFerias> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusFerias restParaModel(){
        return StatusFeriasMapper.INSTANCE.convertToModel(this);
    }
}
