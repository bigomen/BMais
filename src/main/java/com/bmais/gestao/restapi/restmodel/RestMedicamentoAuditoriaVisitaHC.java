package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.MedicamentoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.MedicamentoAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestMedicamentoAuditoriaVisitaHC extends RestModel<MedicamentoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "origem")
    private String origem;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "medicamento")
    private String medicamento;

    @JsonProperty(value = "via")
    private String via;

    @JsonProperty(value = "frequencia")
    private String frequencia;

    @JsonProperty(value = "periodo")
    private String periodo;

    @Override
    public MedicamentoAuditoriaVisitaHC restParaModel(){
        return MedicamentoAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
