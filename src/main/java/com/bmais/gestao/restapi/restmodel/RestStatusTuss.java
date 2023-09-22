package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusTussMapper;
import com.bmais.gestao.restapi.model.StatusTuss;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusTuss extends RestModel<StatusTuss> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusTuss restParaModel() {
        return StatusTussMapper.INSTANCE.convertToModel(this);
    }
}
