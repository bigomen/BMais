package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.RamoAtividadeMapper;
import com.bmais.gestao.restapi.model.RamoAtividade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestRamoAtividade extends RestModel<RamoAtividade> implements Serializable, Comparable<RestRamoAtividade> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public RamoAtividade restParaModel() {
        return RamoAtividadeMapper.INSTANCE.convertToModel(this);
    }

    @Override
    public int compareTo(RestRamoAtividade restRamoAtividade) {
        return StringUtils.compareIgnoreCase(this.descricao, restRamoAtividade.getDescricao());
    }
}
