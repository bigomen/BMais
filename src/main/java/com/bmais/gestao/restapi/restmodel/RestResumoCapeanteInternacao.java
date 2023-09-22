package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.bmais.gestao.restapi.mapper.ResumoMapper;
import com.bmais.gestao.restapi.model.Resumo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestResumoCapeanteInternacao extends RestModel<Resumo> implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "diariasValorApresentado")
	private BigDecimal diariasValorApresentado;
	
	@JsonProperty(value = "diariasValorGlosado")
	private BigDecimal diariasValorGlosado;
	
	@JsonProperty(value = "taxasValorApresentado")
	private BigDecimal taxasValorApresentado;
	
	@JsonProperty(value = "taxasValorGlosado")
	private BigDecimal taxasValorGlosado;
	
	@JsonProperty(value = "gasesValorApresentado")
	private BigDecimal gasesValorApresentado;
	
	@JsonProperty(value = "gasesValorGlosado")
	private BigDecimal gasesValorGlosado;
	
	@JsonProperty(value = "honorariosValorApresentado")
	private BigDecimal honorariosValorApresentado;
	
	@JsonProperty(value = "honorariosValorGlosado")
	private BigDecimal honorariosValorGlosado;
	
	@JsonProperty(value = "sadtValorApresentado")
	private BigDecimal sadtValorApresentado;
	
	@JsonProperty(value = "sadtValorGlosado")
	private BigDecimal sadtValorGlosado;
	
	@JsonProperty(value = "hemoderivadosValorApresentado")
	private BigDecimal hemoderivadosValorApresentado;
	
	@JsonProperty(value = "hemoderivadosValorGlosado")
	private BigDecimal hemoderivadosValorGlosado;
	
	@JsonProperty(value = "materiaisValorApresentado")
	private BigDecimal materiaisValorApresentado;
	
	@JsonProperty(value = "materiaisValorGlosado")
	private BigDecimal materiaisValorGlosado;
	
	@JsonProperty(value = "materiaisEspeciaisValorApresentado")
	private BigDecimal matEspecialValorApresentado;
	
	@JsonProperty(value = "materiaisEspeciaisValorGlosado")
	private BigDecimal matEspecialValorGlosado;
	
	@JsonProperty(value = "medicamentosValorApresentado")
	private BigDecimal medicamentosValorApresentado;
	
	@JsonProperty(value = "medicamentosValorGlosado")
	private BigDecimal medicamentosValorGlosado;
	
	@JsonProperty(value = "pacoteValorApresentado")
	private BigDecimal pacoteValorApresentado;
	
	@JsonProperty(value = "pacoteValorGlosado")
	private BigDecimal pacoteValorGlosado;
	
	@JsonProperty(value = "dataFechamentoConta")
	private LocalDate dataFechamentoConta;
	
	@JsonProperty(value = "observacao")
	private String observacao;
	
	@JsonProperty(value = "desconto")
	private BigDecimal desconto;

    @Override
    public Resumo restParaModel(){
        return ResumoMapper.INSTANCE.convertToModel(this);
    }
}
