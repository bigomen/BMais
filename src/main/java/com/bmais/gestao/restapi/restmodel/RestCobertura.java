package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.mapper.CoberturaMapper;
import com.bmais.gestao.restapi.model.Cobertura;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestCobertura extends RestModel<Cobertura> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "dataInicio")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoInicio;

    @JsonProperty(value = "dataFim")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoFim;

    @JsonProperty(value = "auditor")
    private RestAuditor auditor;

    @JsonProperty(value = "observacao")
    private String observacao;
    
    @JsonProperty(value = "escala")
    private RestEscala  escala;

    @Override
    public Cobertura restParaModel(){
        return CoberturaMapper.INSTANCE.convertToModel(this);
    }
}
