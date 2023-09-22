package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.model.EvolucaoVisita;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestEvolucaoVisita extends RestModel<EvolucaoVisita> implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "descricao")
	private String descricao;
	
	@JsonProperty(value = "data")
	@JsonFormat(pattern = Constantes.PATTERN_DATA)
	private LocalDate dataEvolucao;

	@Override
	public EvolucaoVisita restParaModel()
	{
		return null;
	}

}
