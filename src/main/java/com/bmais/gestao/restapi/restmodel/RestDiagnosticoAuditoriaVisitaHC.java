package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.DiagnosticoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.DiagnosticoAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestDiagnosticoAuditoriaVisitaHC extends RestModel<DiagnosticoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "cid")
    private RestCID cid;

    @JsonProperty(value = "principal")
    private Boolean principal;

    @Override
    public DiagnosticoAuditoriaVisitaHC restParaModel(){
        return DiagnosticoAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
