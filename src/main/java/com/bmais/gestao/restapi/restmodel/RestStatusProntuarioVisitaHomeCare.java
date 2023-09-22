package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;

import com.bmais.gestao.restapi.mapper.StatusProntuarioVisitaHomeCareMapper;
import com.bmais.gestao.restapi.model.StatusProntuarioVisitaHomeCare;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestStatusProntuarioVisitaHomeCare extends RestModel<StatusProntuarioVisitaHomeCare> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusProntuarioVisitaHomeCare restParaModel() {
        return StatusProntuarioVisitaHomeCareMapper.INSTANCE.convertToModel(this);
    }
}
