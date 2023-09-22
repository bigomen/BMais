package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusInternacaoMapper;
import com.bmais.gestao.restapi.model.StatusInternacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusInternacao extends RestModel<StatusInternacao> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusInternacao restParaModel() {
        return StatusInternacaoMapper.INSTANCE.convertToModel(this);
    }
}
