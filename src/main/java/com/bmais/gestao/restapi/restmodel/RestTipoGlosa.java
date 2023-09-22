package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoGlosaMapper;
import com.bmais.gestao.restapi.model.TipoGlosa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoGlosa extends RestModel<TipoGlosa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoGlosa restParaModel(){
        return TipoGlosaMapper.INSTANCE.convertToModel(this);
    }
}
