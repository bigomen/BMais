package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.model.Vinculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestVinculo extends RestModel<Vinculo> implements Serializable
{
    @NotNull(message = "Obrigatório informar o id do hospital")
    @JsonProperty(value = "hospital")
    private RestHospital hospital;

    @NotNull(message = "Obrigatório informar o id do serviço")
    @JsonProperty(value = "servico")
    private RestServico servico;

    @NotNull(message = "Obrigatório informar o id do cliente")
    @JsonProperty(value = "cliente")
    private RestCliente cliente;
    
    @JsonProperty(value = "auditor")
    private RestAuditor auditor;

    @NotNull(message = "Obrigatório informar o id da categoria do serviço")
    @JsonProperty(value = "categoriaServico")
    private RestCategoriaServico categoriaServico;

    @JsonProperty(value = "dataInicio")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInicio;

    @JsonProperty(value = "dataFim")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataFim;

    @Override
    public Vinculo restParaModel()
    {
        return null;
    }
}
