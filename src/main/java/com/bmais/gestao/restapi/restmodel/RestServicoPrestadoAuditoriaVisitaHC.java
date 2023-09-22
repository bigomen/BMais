package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ServicoPrestadoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.ServicoPrestadoAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class RestServicoPrestadoAuditoriaVisitaHC extends RestModel<ServicoPrestadoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "profissional")
    private String profissional;

    @JsonProperty(value = "quantidade")
    private Integer quantidade;

    @JsonProperty(value = "frequencia")
    private String frequencia;

    @JsonProperty(value = "servico")
    private String servico;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "assistencia_adequada")
    private Boolean assistenciaAdequada;

    @Override
    public ServicoPrestadoAuditoriaVisitaHC restParaModel(){
        return ServicoPrestadoAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
