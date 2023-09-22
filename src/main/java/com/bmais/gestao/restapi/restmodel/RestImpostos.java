package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ImpostosMapper;
import com.bmais.gestao.restapi.model.Impostos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestImpostos extends RestModel<Impostos> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "valor")
    private BigDecimal valor;


    @Override
    public Impostos restParaModel(){
        return ImpostosMapper.INSTANCE.restParaModel(this);
    }
}
