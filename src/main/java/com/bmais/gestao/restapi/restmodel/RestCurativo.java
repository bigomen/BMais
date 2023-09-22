package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;

import com.bmais.gestao.restapi.mapper.CurativoMapper;
import com.bmais.gestao.restapi.model.Curativo;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestCurativo extends RestModel<Curativo> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "local")
    private String local;

    @JsonProperty(value = "complexidade")
    private String complexidade;

    @JsonProperty(value = "tamanho")
    private String tamanho;

    @Override
    public Curativo restParaModel(){
        return CurativoMapper.INSTANCE.convertToModel(this);
    }
}
