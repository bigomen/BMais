package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.SintomaQuadroClinicoMapper;
import com.bmais.gestao.restapi.model.SintomaQuadroClinico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestSintomaQuadroClinico extends RestModel<SintomaQuadroClinico> implements Serializable {


    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public SintomaQuadroClinico restParaModel(){
        return SintomaQuadroClinicoMapper.INSTANCE.convertToModel(this);
    }
}
