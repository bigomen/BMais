package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ObservacaoAvaliacaoMapper;
import com.bmais.gestao.restapi.model.ObservacaoAvaliacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestObservacaoAvaliacao extends RestModel<ObservacaoAvaliacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public ObservacaoAvaliacao restParaModel(){
        return ObservacaoAvaliacaoMapper.INSTANCE.convertToModel(this);
    }
}
