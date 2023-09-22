package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoAgendaMapper;
import com.bmais.gestao.restapi.model.TipoAgenda;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoAgenda extends RestModel<TipoAgenda> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoAgenda restParaModel(){
        return TipoAgendaMapper.INSTANCE.convertToModel(this);
    }
}
