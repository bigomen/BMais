package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.GlosaMapper;
import com.bmais.gestao.restapi.model.Glosa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestGlosa extends RestModel<Glosa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "responsabilidade")
    private String responsabilidade;

    @JsonProperty(value = "abrirDiaria")
    private Boolean abrirDiaria;

    @JsonProperty(value = "status")
    private RestStatusGlosa status;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public Glosa restParaModel() {
        return GlosaMapper.INSTANCE.convertToModel(this);
    }
}
