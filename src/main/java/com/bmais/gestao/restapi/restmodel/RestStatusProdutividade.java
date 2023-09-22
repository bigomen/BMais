package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusProdutividadeMapper;
import com.bmais.gestao.restapi.model.StatusProdutividade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestStatusProdutividade extends RestModel<StatusProdutividade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusProdutividade restParaModel(){
        return StatusProdutividadeMapper.INSTANCE.convertToModel(this);
    }
}
