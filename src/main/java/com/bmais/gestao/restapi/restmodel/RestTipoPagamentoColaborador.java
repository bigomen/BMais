package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoPagamentoColaboradorMapper;
import com.bmais.gestao.restapi.model.TipoPagamentoColaborador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestTipoPagamentoColaborador extends RestModel<TipoPagamentoColaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoPagamentoColaborador restParaModel(){
        return TipoPagamentoColaboradorMapper.INSTANCE.convertToModel(this);
    }
}
