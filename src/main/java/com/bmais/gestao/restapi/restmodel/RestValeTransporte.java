package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ValeTransporteMapper;
import com.bmais.gestao.restapi.model.ValeTransporte;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestValeTransporte extends RestModel<ValeTransporte> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "data")
    private LocalDate data;

    @JsonProperty(value = "quantidade")
    private Long quantidade;

    @JsonProperty(value = "tipo")
    private RestTipoValeTransporte tipo;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public ValeTransporte restParaModel(){
        return ValeTransporteMapper.INSTANCE.convertToModel(this);
    }
}
