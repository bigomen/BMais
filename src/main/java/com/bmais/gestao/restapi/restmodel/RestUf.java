package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.UfMapper;
import com.bmais.gestao.restapi.model.UF;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestUf extends RestModel<UF> implements Serializable, Comparable<RestUf> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "sigla")
    private String sigla;

    @Override
    public UF restParaModel() {
        return UfMapper.INSTANCE.convertToModel(this);
    }

    @Override
    public int compareTo(RestUf restUf) {
        return StringUtils.compareIgnoreCase(this.descricao, restUf.getDescricao());
    }
}
