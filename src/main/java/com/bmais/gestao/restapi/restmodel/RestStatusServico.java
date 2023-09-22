package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusServicoMapper;
import com.bmais.gestao.restapi.model.StatusServico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusServico extends RestModel<StatusServico> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusServico restParaModel() {
        return StatusServicoMapper.INSTANCE.convertToModel(this);
    }
}
