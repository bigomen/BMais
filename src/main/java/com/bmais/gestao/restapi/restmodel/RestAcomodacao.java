package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.AcomodacaoMapper;
import com.bmais.gestao.restapi.model.Acomodacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestAcomodacao extends RestModel<Acomodacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public Acomodacao restParaModel(){
        return AcomodacaoMapper.INSTANCE.convertToModel(this);
    }
}
