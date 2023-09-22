package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.CategoriaServicoMapper;
import com.bmais.gestao.restapi.model.CategoriaServico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestCategoriaServico extends RestModel<CategoriaServico> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "valor")
    private BigDecimal valor;

    @JsonProperty(value = "categoria")
    private Integer categoria;

    @Override
    public CategoriaServico restParaModel(){
        return CategoriaServicoMapper.INSTANCE.convertToModel(this);
    }

}
