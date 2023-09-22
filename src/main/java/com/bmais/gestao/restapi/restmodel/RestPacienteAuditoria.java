package com.bmais.gestao.restapi.restmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RestPacienteAuditoria {

    @JsonProperty(value = "visitas")
    private Long numeroVisitas;

    @JsonProperty(value = "ultima_internacao")
    private LocalDate ultimaInternacao;

    @JsonProperty(value = "ultima_visita")
    private LocalDate ultimaVisita;

    @JsonProperty(value = "ultimo_prontuario")
    private Long ultimoProntuario;
}
