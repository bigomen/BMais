package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoValeTransporteMapper;
import com.bmais.gestao.restapi.model.TipoValeTransporte;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoValeTransporte extends RestModel<TipoValeTransporte> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoValeTransporte restParaModel(){
        return TipoValeTransporteMapper.INSTANCE.convertToModel(this);
    }
}
