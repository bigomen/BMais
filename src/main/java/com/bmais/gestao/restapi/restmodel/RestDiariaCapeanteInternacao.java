package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestDiariaCapeanteInternacao implements Serializable
{
    private static final long serialVersionUID = 1L;

	@JsonProperty(value = "apdiariaCobrada")
	private BigDecimal apdiariaCobrada;
	
	@JsonProperty(value = "apDiariaGlosada")
	private BigDecimal apDiariaGlosada;
	
	@JsonProperty(value = "apValorApresentado")
	private BigDecimal apValorApresentado;
	
	@JsonProperty(value = "apValorGlosado")
	private BigDecimal apValorGlosado;
	
	@JsonProperty(value = "enfDiariaCobrada")
	private BigDecimal enfDiariaCobrada;
	
	@JsonProperty(value = "enfDiariaGlosada")
	private BigDecimal enfDiariaGlosada;
	
	@JsonProperty(value = "enfValorApresentado")
	private BigDecimal enfValorApresentado;
	
	@JsonProperty(value = "enfValorGlosado")
	private BigDecimal enfValorGlosado;
	
	@JsonProperty(value = "semiDiariaCobrada")
	private BigDecimal semiDiariaCobrada;
	
	@JsonProperty(value = "semiDiariaglosada")
	private BigDecimal semiDiariaglosada;
	
	@JsonProperty(value = "semiValorApresentado")
	private BigDecimal semiValorApresentado;
	
	@JsonProperty(value = "semiValorGlosado")
	private BigDecimal semiValorGlosado;
	
	@JsonProperty(value = "utiDiariaCobrada")
	private BigDecimal utiDiariaCobrada;
	
	@JsonProperty(value = "utiDiariaGlosada")
	private BigDecimal utiDiariaGlosada;
	
	@JsonProperty(value = "utiValorApresentado")
	private BigDecimal utiValorApresentado;
	
	@JsonProperty(value = "utiValorGlosado")
	private BigDecimal utiValorGlosado;
	
	@JsonProperty(value = "bercarioDiariaCobrada")
	private BigDecimal bercarioDiariaCobrada;
	
	@JsonProperty(value = "bercarioDiariaGlosada")
	private BigDecimal bercarioDiariaGlosada;
	
	@JsonProperty(value = "bercarioValorApresentado")
	private BigDecimal bercarioValorApresentado;
	
	@JsonProperty(value = "bercarioValorGlosado")
	private BigDecimal bercarioValorGlosado;
	
	@JsonProperty(value = "dayClinicDiariaCobrada")
	private BigDecimal dayClinicDiariaCobrada;
	
	@JsonProperty(value = "dayClinicDiariaGlosada")
	private BigDecimal dayClinicDiariaGlosada;
	
	@JsonProperty(value = "dayClinicValorApresentado")
	private BigDecimal dayClinicValorApresentado;
	
	@JsonProperty(value = "dayClinicValorGlosado")
	private BigDecimal dayClinicValorGlosado;
	
	@JsonProperty(value = "acompanhanteDiariaCobrada")
	private BigDecimal acompanhanteDiariaCobrada;
	
	@JsonProperty(value = "acompanhanteDiariaGlosada")
	private BigDecimal acompanhanteDiariaGlosada;
	
	@JsonProperty(value = "acompanhanteValorApresentado")
	private BigDecimal acompanhanteValorApresentado;
	
	@JsonProperty(value = "acompanhanteValorGlosado")
	private BigDecimal acompanhanteValorGlosado;
	
	@JsonProperty(value = "isolamento")
	private Boolean isolamento;
	
	@JsonProperty(value = "observacao")
	private String observacao;
}
