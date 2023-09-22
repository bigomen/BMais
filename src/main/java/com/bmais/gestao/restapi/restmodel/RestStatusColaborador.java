package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusColaboradorMapper;
import com.bmais.gestao.restapi.model.StatusColaborador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusColaborador extends RestModel<StatusColaborador> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusColaborador restParaModel() {
        return StatusColaboradorMapper.INSTANCE.convertToModel(this);
    }
}
