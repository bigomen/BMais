package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoPessoaJuridicaMapper;
import com.bmais.gestao.restapi.model.TipoPessoaJuridica;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoPessoaJuridica extends RestModel<TipoPessoaJuridica> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoPessoaJuridica restParaModel() {
        return TipoPessoaJuridicaMapper.INSTANCE.convertToModel(this);
    }
}
