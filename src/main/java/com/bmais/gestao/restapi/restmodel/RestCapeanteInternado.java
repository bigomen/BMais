package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.model.enums.TipoInternacao;
import com.bmais.gestao.restapi.model.enums.TipoTratamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestCapeanteInternado implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @JsonProperty(value = "recemNascido")
	public Boolean rn = false;
	
	@JsonProperty(value = "nomeRN")
	public String nomeRn;
	
	@JsonProperty(value = "gemelar")
	public Integer gemelar;
	
	@JsonProperty(value = "dataNascimentoRN")
	public LocalDate dataNascimentoRn;
	
	@JsonProperty(value = "sexoRN")
	public Sexo sexoRn;
	
	@JsonProperty(value = "parcial")
	private Boolean parcial = false;
	
	@JsonProperty(value = "primeiraParcial")
	private Boolean primeiraParcial = false;
	
	@JsonProperty(value = "parcialFinal")
	private Boolean parcialFinal = false;
	
	@JsonProperty(value = "inicioCobranca")
	private LocalDate inicioCobranca;
	
	@JsonProperty(value = "fimCobranca")
	private LocalDate fimCobranca;
	
	@JsonProperty(value = "complemento")
	private Boolean complemento = false;
	
	@JsonProperty(value = "pacote")
	private Boolean pacote = false;
	
	@JsonProperty(value = "dayclinic")
	private Boolean dayclinic = false;

	@JsonProperty(value = "observacao")
	private String observacao;
	
	@JsonProperty(value = "medico")
	private RestAuditor medico;

	@JsonProperty(value = "enfermeiro")
	private RestAuditor enfermeiro;

	@JsonProperty(value = "status")
	private RestStatusCapeante status;
	
	@JsonProperty(value = "diagnosticoPrincipal")
	private RestCID cid;
	
	@JsonProperty(value = "tipoInternacao")
	private TipoInternacao tipoInternacao;
	
	@JsonProperty(value = "tipoTratamento")
	private TipoTratamento tipoTratamento;
}
