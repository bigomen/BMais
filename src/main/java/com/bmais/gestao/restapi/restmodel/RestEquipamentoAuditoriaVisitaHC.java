package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EquipamentoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.EquipamentoAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestEquipamentoAuditoriaVisitaHC extends RestModel<EquipamentoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "equipamento")
    private String equipamento;

    @JsonProperty(value = "proprio")
    private Boolean proprio;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public EquipamentoAuditoriaVisitaHC restParaModel(){
        return EquipamentoAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
