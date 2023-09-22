package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.StatusVisitaMapper;
import com.bmais.gestao.restapi.model.StatusVisita;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusVisita extends RestModel<StatusVisita> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusVisita restParaModel(){
        return StatusVisitaMapper.INSTANCE.convertToModel(this);
    }


}
