package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ResumoCapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.model.ResumoCapeantePSAmbulatorio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class RestResumoCapeantePSAmbulatorio extends RestModel<ResumoCapeantePSAmbulatorio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "paciente")
    private String paciente;

    @JsonProperty(value = "valorCobrado")
    private BigDecimal valorCobrado;

    @JsonProperty(value = "valorGlosado")
    private BigDecimal valorGlosado;

    @JsonProperty(value = "valorLiberado")
    private BigDecimal valorLiberado;

    @Override
    public ResumoCapeantePSAmbulatorio restParaModel(){
        return ResumoCapeantePSAmbulatorioMapper.INSTANCE.convertToModel(this);
    }
}
