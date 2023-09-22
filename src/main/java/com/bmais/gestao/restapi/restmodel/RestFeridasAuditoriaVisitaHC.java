package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.FeridasAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.FeridasAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestFeridasAuditoriaVisitaHC extends RestModel<FeridasAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "local")
    private String local;

    @JsonProperty(value = "tamanho")
    private String tamanho;

    @JsonProperty(value = "aspecto")
    private String aspecto;

    @Override
    public FeridasAuditoriaVisitaHC restParaModel(){
        return FeridasAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
