package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.BeneficioMapper;
import com.bmais.gestao.restapi.model.Beneficio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RestBeneficio extends RestModel<Beneficio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "faixa_etaria_inicial")
    private Long faixaEtariaInicial;

    @JsonProperty(value = "faixa_etaria_final")
    private Long faixaEtariaFinal;

    @JsonProperty(value = "valor_oferecido")
    private BigDecimal valorOferecido;

    @JsonProperty(value = "valor_beneficio")
    private BigDecimal valorBeneficio;

    @Override
    public Beneficio restParaModel(){
        return BeneficioMapper.INSTANCE.convertToModel(this);
    }
}
