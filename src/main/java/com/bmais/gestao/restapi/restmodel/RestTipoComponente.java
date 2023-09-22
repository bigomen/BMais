package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.TipoComponenteMapper;
import com.bmais.gestao.restapi.model.TipoComponente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestTipoComponente extends RestModel<TipoComponente> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;
    
    @JsonProperty(value = "no")
    private String no;

    @Override
    public TipoComponente restParaModel(){
        return TipoComponenteMapper.INSTANCE.convertToModel(this);
    }
}
