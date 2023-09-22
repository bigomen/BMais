package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ConclusaoAuditoriaHCMapper;
import com.bmais.gestao.restapi.model.ConclusaoAuditoriaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class RestConclusaoAuditoriaHC extends RestModel<ConclusaoAuditoriaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public ConclusaoAuditoriaHC restParaModel(){
        return ConclusaoAuditoriaHCMapper.INSTANCE.convertToModel(this);
    }
}
