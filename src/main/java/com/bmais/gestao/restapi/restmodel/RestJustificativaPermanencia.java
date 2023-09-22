package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.JustificativaPermanenciaMapper;
import com.bmais.gestao.restapi.model.JustificativaPermanencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestJustificativaPermanencia extends RestModel<JustificativaPermanencia> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public JustificativaPermanencia restParaModel(){
        return JustificativaPermanenciaMapper.INSTANCE.convertToModel(this);
    }
}
