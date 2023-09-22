package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.CTPSMapper;
import com.bmais.gestao.restapi.model.CTPS;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestCTPS implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "numero")
    private String numero;

    @JsonProperty(value = "serie")
    private String serie;

    @JsonProperty(value = "uf")
    private RestUf uf;

    public CTPS restParaModel() {
        return CTPSMapper.INSTANCE.convertToModel(this);
    }
}
