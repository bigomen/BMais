package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusAuditorMapper;
import com.bmais.gestao.restapi.model.StatusAuditor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class RestStatusAuditor extends RestModel<StatusAuditor> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusAuditor restParaModel(){return StatusAuditorMapper.INSTANCE.convertToModel(this);}
}
