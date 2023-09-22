package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.BancoMapper;
import com.bmais.gestao.restapi.model.Banco;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestBanco extends RestModel<Banco> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "nome")
    private String nome;

    @Override
    public Banco restParaModel() {
        return BancoMapper.INSTANCE.convertToModel(this);
    }
}
