package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.model.ClienteServico;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RestClienteServico extends RestModel<ClienteServico> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "servico")
    private RestServico servico;

    @JsonProperty("dataInicio")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInicio;

    @JsonProperty("dataFim")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataFim;

    @JsonProperty("valor")
    private BigDecimal valor;

    @Override
    public ClienteServico restParaModel(){
//        return ClienteServicoMapper.INSTANCE.convertToModel(this);
        return null;
    }
}
