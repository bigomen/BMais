package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.MunicipioMapper;
import com.bmais.gestao.restapi.model.Municipio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestMunicipio extends RestModel<Municipio> implements Serializable, Comparable<RestMunicipio> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "uf")
    private RestUf uf;

    @Override
    public Municipio restParaModel() {
        return MunicipioMapper.INSTANCE.convertToModel(this);
    }

    @Override
    public int compareTo(RestMunicipio restMunicipio) {
        return StringUtils.compareIgnoreCase(this.nome, restMunicipio.getNome());
    }
}
