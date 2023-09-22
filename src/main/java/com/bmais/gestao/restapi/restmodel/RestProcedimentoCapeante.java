package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.ProcedimentoCapeanteMapper;
import com.bmais.gestao.restapi.model.ProcedimentoCapeante;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestProcedimentoCapeante extends RestModel<ProcedimentoCapeante> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tuss")
    private RestTuss tuss;

    @JsonProperty(value = "data")
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate data;

    @JsonProperty(value = "horaInicio")
    @DateTimeFormat(pattern = Constantes.PATTERN_HORA)
    private LocalTime horaInicio;

    @JsonProperty(value = "horaFim")
    @DateTimeFormat(pattern = Constantes.PATTERN_HORA)
    private LocalTime horaFim;

    @JsonProperty(value = "porcProcedimento")
    private BigDecimal porcProcedimento;

    @Override
    public ProcedimentoCapeante restParaModel(){
        return ProcedimentoCapeanteMapper.INSTANCE.convertToModel(this);
    }
}
