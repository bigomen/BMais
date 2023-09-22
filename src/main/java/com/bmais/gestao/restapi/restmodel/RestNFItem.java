package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.NFItemMapper;
import com.bmais.gestao.restapi.model.NFItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestNFItem extends RestModel<NFItem> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "servico")
    private String servico;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "quantidade")
    private Integer quantidade;

    @JsonProperty(value = "valor")
    private BigDecimal valor;


    @Override
    public NFItem restParaModel(){
        return NFItemMapper.INSTANCE.restParaModel(this);
    }
}
