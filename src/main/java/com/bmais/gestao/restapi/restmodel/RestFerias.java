package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.FeriasMapper;
import com.bmais.gestao.restapi.model.Ferias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestFerias extends RestModel<Ferias> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "data_inicio")
    private LocalDate dataInicio;

    @JsonProperty(value = "data_fim")
    private LocalDate dataFim;

    @JsonProperty(value = "data_limite")
    private LocalDate dataLimite;

    @JsonProperty(value = "gozo_inicio_1")
    private LocalDate gozoInicio1;

    @JsonProperty(value = "gozo_fim_1")
    private LocalDate gozoFim1;

    @JsonProperty(value = "gozo_inicio_2")
    private LocalDate gozoInicio2;

    @JsonProperty(value = "gozo_fim_2")
    private LocalDate gozoFim2;

    @JsonProperty(value = "gozo_inicio_3")
    private LocalDate gozoInicio3;

    @JsonProperty(value = "gozo_fim_3")
    private LocalDate gozoFim3;

    @JsonProperty(value = "vendas_dias")
    private Long vendasDias;

    @JsonProperty(value = "quantidade_dias")
    private Long quantidadeDias;

    @JsonProperty(value = "status")
    private RestStatusFerias status;

    @Override
    public Ferias restParaModel(){
        return FeriasMapper.INSTANCE.convertToModel(this);
    }
}
