package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.AvaliacaoRelatorioMapper;
import com.bmais.gestao.restapi.model.AvaliacaoRelatorio;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class RestAvaliacaoRelatorio extends RestModel<AvaliacaoRelatorio> implements Serializable, Comparable<RestAvaliacaoRelatorio> {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "label")
    private String descricao;

    @JsonProperty(value = "checked")
    private boolean marcado;
//    @JsonProperty(value = "antecedentes")
//    private Boolean antecedentes;
//
//    @JsonProperty(value = "internacoes")
//    private Boolean internacoes;
//
//    @JsonProperty(value = "cirurgias")
//    private Boolean cirurgias;
//
//    @JsonProperty(value = "po_uti")
//    private Boolean poUti;
//
//    @JsonProperty(value = "transferencia")
//    private Boolean transferencia;
//
//    @JsonProperty(value = "continuidade")
//    private Boolean continuidade;
//
//    @JsonProperty(value = "siglas")
//    private Boolean siglas;
//
//    @JsonProperty(value = "laudos")
//    private Boolean laudos;
//
//    @JsonProperty(value = "gravidade")
//    private Boolean gravidade;
//
//    @JsonProperty(value = "motivo")
//    private Boolean motivo;
//
//    @JsonProperty(value = "curativos")
//    private Boolean curativos;
//
//    @JsonProperty(value = "dietas")
//    private Boolean dietas;
//
//    @JsonProperty(value = "drogas")
//    private Boolean drogas;
//
//    @JsonProperty(value = "equipamentos")
//    private Boolean equipamentos;
//
//    @JsonProperty(value = "gasoterapia")
//    private Boolean gasoterapia;
//
//    @JsonProperty(value = "medicamentos")
//    private Boolean medicamentos;
//
//    @JsonProperty(value = "hemodialise")
//    private Boolean hemodialise;
//
//    @JsonProperty(value = "sangue")
//    private Boolean sangue;
//
//    @JsonProperty(value = "fisioterapia")
//    private Boolean fisioterapia;
//
//    @JsonProperty(value = "procedimentos")
//    private Boolean procedimentos;

    @Override
    public AvaliacaoRelatorio restParaModel(){
        return AvaliacaoRelatorioMapper.INSTANCE.convertToModel(this);
    }
    
	@Override
	public int compareTo(RestAvaliacaoRelatorio o)
	{
		return this.descricao.compareTo(o.descricao);
	}
}
