package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.DependenteMapper;
import com.bmais.gestao.restapi.model.Dependente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestDependente extends RestModel<Dependente> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "numeroDependente")
    private Long numeroDependente;

    @JsonProperty(value = "nascimento")
    private LocalDate nascimento;

    @JsonProperty(value = "rg")
    private String rg;

    @JsonProperty(value = "cpf")
    private String cpf;

    @JsonProperty(value = "sus")
    private String sus;

    @JsonProperty(value = "beneficios")
    private Collection<RestColaboradorBeneficio> colaboradorBeneficios;

    @Override
    public Dependente restParaModel() {
        return DependenteMapper.INSTANCE.convertToModel(this);
    }
}
