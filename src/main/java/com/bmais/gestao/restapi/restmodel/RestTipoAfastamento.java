package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoAfastamentoMapper;
import com.bmais.gestao.restapi.model.TipoAfastamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoAfastamento extends RestModel<TipoAfastamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoAfastamento restParaModel(){
        return TipoAfastamentoMapper.INSTANCE.convertToModel(this);
    }
}
