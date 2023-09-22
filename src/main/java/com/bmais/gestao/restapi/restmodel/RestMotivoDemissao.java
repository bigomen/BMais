package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.MotivoDemissaoMapper;
import com.bmais.gestao.restapi.model.MotivoDemissao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestMotivoDemissao extends RestModel<MotivoDemissao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public MotivoDemissao restParaModel(){
        return MotivoDemissaoMapper.INSTANCE.convertToModel(this);
    }
}
