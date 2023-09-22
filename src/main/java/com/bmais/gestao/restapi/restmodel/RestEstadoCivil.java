package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EstadoCivilMapper;
import com.bmais.gestao.restapi.model.EstadoCivil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestEstadoCivil extends RestModel<EstadoCivil> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public EstadoCivil restParaModel() {
        return EstadoCivilMapper.INSTANCE.convertToModel(this);
    }
}
