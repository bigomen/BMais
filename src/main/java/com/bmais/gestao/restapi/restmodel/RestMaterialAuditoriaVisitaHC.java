package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.MaterialAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.model.MaterialAuditoriaVisitaHC;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestMaterialAuditoriaVisitaHC extends RestModel<MaterialAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "material")
    private String material;

    @JsonProperty(value = "quantidade")
    private Integer quantidade;

    @JsonProperty(value = "frequencia")
    private String frequencia;

    @JsonProperty(value = "produto")
    private String produto;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public MaterialAuditoriaVisitaHC restParaModel(){
        return MaterialAuditoriaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
