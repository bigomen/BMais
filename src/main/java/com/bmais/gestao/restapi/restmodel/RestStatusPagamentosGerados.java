package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusPagamentosGeradosMapper;
import com.bmais.gestao.restapi.model.StatusPagamentosGerados;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestStatusPagamentosGerados extends RestModel<StatusPagamentosGerados> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusPagamentosGerados restParaModel(){
        return StatusPagamentosGeradosMapper.INSTANCE.convertToModel(this);
    }
}
