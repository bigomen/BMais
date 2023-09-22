package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EscolaridadeMapper;
import com.bmais.gestao.restapi.model.Escolaridade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestEscolaridade extends RestModel<Escolaridade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public Escolaridade restParaModel() {
        return EscolaridadeMapper.INSTANCE.convertToModel(this);
    }
}
