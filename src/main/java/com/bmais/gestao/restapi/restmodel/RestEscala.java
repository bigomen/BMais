package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;

@Data
public class RestEscala implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "fakeId", access = Access.READ_ONLY)
	private Long fakeId;

	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "cliente")
	private RestCliente cliente;
	
	@JsonProperty(value = "hospital")
	private RestHospital hospital;
	
	@JsonProperty(value = "servico")
	private RestServico servico;
	
	@JsonProperty(value = "auditor")
	private RestAuditor auditor;
	
	@JsonProperty(value = "dataInicio")
	private LocalDate dataInicio;
	
	@JsonProperty(value = "dataFim")
	private LocalDate dataFim;
	
	@JsonProperty(value = "cobertura")
	private RestCobertura cobertura;
}
