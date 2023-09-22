package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusHospitalMapper;
import com.bmais.gestao.restapi.model.StatusHospital;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusHospital extends RestModel<StatusHospital> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusHospital restParaModel() {
        return StatusHospitalMapper.INSTANCE.convertToModel(this);
    }
}
