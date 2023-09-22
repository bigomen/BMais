package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StatusNFMapper;
import com.bmais.gestao.restapi.model.StatusNF;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStatusNF extends RestModel<StatusNF> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public StatusNF restParaModel(){
        return StatusNFMapper.INSTANCE.restParaModel(this);
    }
}
