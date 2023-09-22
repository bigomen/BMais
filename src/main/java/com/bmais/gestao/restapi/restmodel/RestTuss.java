package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TussMapper;
import com.bmais.gestao.restapi.model.Tuss;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTuss extends RestModel<Tuss> implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "status")
    private RestStatusTuss status;

    @Override
    public Tuss restParaModel() {
        return TussMapper.INSTANCE.convertToModel(this);
    }
}
