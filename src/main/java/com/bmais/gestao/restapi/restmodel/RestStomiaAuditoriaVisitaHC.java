package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.StomiaAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.StomiaAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestStomiaAuditoriaVisitaHC extends RestModel<StomiaAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "stomia")
    private String stomia;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public StomiaAuditoriaVisitaHC restParaModel(){
        return StomiaAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
