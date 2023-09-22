package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.AcomodacaoVisitaMapper;
import com.bmais.gestao.restapi.model.AcomodacaoVisita;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class RestAcomodacaoVisita extends RestModel<AcomodacaoVisita> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tipo")
    private RestAcomodacao acomodacao;

    @JsonProperty(value = "dataEntrada")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicio;

    @JsonProperty(value = "dataSaida")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFim;

    @Override
    public AcomodacaoVisita restParaModel(){
        return AcomodacaoVisitaMapper.INSTANCE.convertToModel(this);
    }
}
