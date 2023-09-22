package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.AvaliacaoMapper;
import com.bmais.gestao.restapi.model.Avaliacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestAvaliacao extends RestModel<Avaliacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public Avaliacao restParaModel(){
        return AvaliacaoMapper.INSTANCE.convertToModel(this);
    }
}
