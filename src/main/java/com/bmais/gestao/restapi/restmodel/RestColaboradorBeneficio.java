package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ColaboradorBeneficioMapper;
import com.bmais.gestao.restapi.model.ColaboradorBeneficio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestColaboradorBeneficio extends RestModel<ColaboradorBeneficio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "beneficio")
    private RestBeneficio beneficio;

    @JsonProperty(value = "data_adesao")
    private LocalDate dataAdesao;

    @JsonProperty(value = "data_fim")
    private LocalDate dataFim;

    @JsonProperty(value = "valor_oferecido")
    private BigDecimal valorOferecido;

    @JsonProperty(value = "valor_contratado")
    private BigDecimal valorContratado;

    @JsonProperty(value = "valor_descontado")
    private BigDecimal valorDescontado;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public ColaboradorBeneficio restParaModel(){
        return ColaboradorBeneficioMapper.INSTANCE.convertToModel(this);
    }
}
