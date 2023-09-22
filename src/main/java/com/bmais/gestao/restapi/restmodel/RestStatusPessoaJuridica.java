package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusPessoaJuridicaMapper;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusPessoaJuridica extends RestModel<StatusPessoaJuridica> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusPessoaJuridica restParaModel() {
        return StatusPessoaJuridicaMapper.INSTANCE.convertToModel(this);
    }
}
