package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ComponenteAltoCustoMapper;
import com.bmais.gestao.restapi.model.ComponenteAltoCusto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestComponenteAltoCusto extends RestModel<ComponenteAltoCusto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tipo")
    private RestTipoComponente tipo;

    @JsonProperty(value = "marca_tipo")
    private String marcaTipo;

    @JsonProperty(value = "quantidade")
    private Long quantidade;

    @Override
    public ComponenteAltoCusto restParaModel(){
        return ComponenteAltoCustoMapper.INSTANCE.convertToModel(this);
    }
}
