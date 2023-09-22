package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.bmais.gestao.restapi.mapper.CIDMapper;
import com.bmais.gestao.restapi.model.CID;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestCID extends RestModel<CID> implements Serializable, Comparable<RestCID> {

    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "status")
    private RestStatusCID status;

    @Override
    public CID restParaModel() {
        return CIDMapper.INSTANCE.convertToModel(this);
    }

    @Override
    public int compareTo(RestCID restCID) {
        return StringUtils.compareIgnoreCase(this.codigo, restCID.getCodigo());
    }
}
