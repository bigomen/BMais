package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusEmpresaMapper;
import com.bmais.gestao.restapi.model.StatusEmpresa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusEmpresa extends RestModel<StatusEmpresa> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusEmpresa restParaModel(){return StatusEmpresaMapper.INSTANCE.convertToModel(this);}
}
