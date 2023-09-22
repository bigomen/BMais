package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.DiariaMapper;
import com.bmais.gestao.restapi.model.Diaria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestDiaria extends RestModel<Diaria> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "idLocal")
    private String idLocal;

    @JsonProperty(value = "diarias_cobradas")
    private Integer cobradas;

    @JsonProperty(value = "diarias_glosadas")
    private Integer glosadas;

    @JsonProperty(value = "valor_apresentado")
    private BigDecimal valorApresentado;

    @JsonProperty(value = "valor_glosa")
    private BigDecimal valorGlosa;

    @JsonProperty(value = "acomodacao")
    private RestAcomodacao acomodacao;

    @Override
    public Diaria restParaModel(){
        return DiariaMapper.INSTANCE.convertToModel(this);
    }
}
