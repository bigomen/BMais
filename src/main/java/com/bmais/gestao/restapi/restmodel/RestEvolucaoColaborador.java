package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EvolucaoColaboradorMapper;
import com.bmais.gestao.restapi.model.EvolucaoColaborador;
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
public class RestEvolucaoColaborador extends RestModel<EvolucaoColaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "data_inicio")
    private LocalDate dataInicio;

    @JsonProperty(value = "area_setor")
    private String areaSetor;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "salario")
    private BigDecimal salario;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @JsonProperty(value = "cargo")
    private RestCargo cargo;

    @JsonProperty(value = "data_alteracao")
    private LocalDate dataAlteracao;

    @Override
    public EvolucaoColaborador restParaModel(){
        return EvolucaoColaboradorMapper.INSTANCE.convertToModel(this);
    }
}
