package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.CargoMapper;
import com.bmais.gestao.restapi.model.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestCargo extends RestModel<Cargo> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public Cargo restParaModel(){
        return CargoMapper.INSTANCE.convertToModel(this);
    }
}
