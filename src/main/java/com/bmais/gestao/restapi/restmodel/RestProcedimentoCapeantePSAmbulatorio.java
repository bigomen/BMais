package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ProcedimentoCapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.model.ProcedimentoCapeantePSAmbulatorio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestProcedimentoCapeantePSAmbulatorio extends RestModel<ProcedimentoCapeantePSAmbulatorio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome_paciente")
    private String nomePaciente;

    @JsonProperty(value = "valor_cobrado")
    private BigDecimal valorCobrado;

    @JsonProperty(value = "valor_glosado")
    private BigDecimal valorGlosado;

    @JsonProperty(value = "valor_liberado")
    private BigDecimal valorLiberado;

    @Override
    public ProcedimentoCapeantePSAmbulatorio restParaModel(){
        return ProcedimentoCapeantePSAmbulatorioMapper.INSTANCE.convertToModel(this);
    }
}
