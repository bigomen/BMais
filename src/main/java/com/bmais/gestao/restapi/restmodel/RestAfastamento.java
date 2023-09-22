package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.AfastamentoMapper;
import com.bmais.gestao.restapi.model.Afastamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestAfastamento extends RestModel<Afastamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tipo_afastamento")
    private RestTipoAfastamento tipo;

    @JsonProperty(value = "cid")
    private RestCID cid;

    @JsonProperty(value = "data_inicio")
    private LocalDate dataInicio;

    @JsonProperty(value = "data_previsao")
    private LocalDate dataPrevisao;

    @JsonProperty(value = "dias_previstos")
    private Long diasPrevistos;

    @JsonProperty(value = "data_retorno")
    private LocalDate dataRetorno;

    @JsonProperty(value = "dias_reais")
    private Long diasReais;

    @JsonProperty(value = "documentos")
    private Collection<RestDocumento> documentos;

    @Override
    public Afastamento restParaModel(){
        return AfastamentoMapper.INSTANCE.convertToModel(this);
    }
}
