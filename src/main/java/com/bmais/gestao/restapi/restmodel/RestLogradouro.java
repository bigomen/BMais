package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.bmais.gestao.restapi.mapper.LogradouroMapper;
import com.bmais.gestao.restapi.model.Logradouro;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestLogradouro extends  RestModel<Logradouro> implements Serializable, Comparable<RestLogradouro> {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tipo")
    private String tipo;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "cidade")
    private String cidade;

    @JsonProperty(value = "uf")
    private String uf;

    @Override
    public Logradouro restParaModel(){return LogradouroMapper.INSTANCE.convertToModel(this);}

    @Override
    public int compareTo(RestLogradouro restLogradouro){
        return StringUtils.compareIgnoreCase(this.nome, restLogradouro.getNome());
    }
}
