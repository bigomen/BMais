package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.JoinColumn;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.MovimentacaoBancariaMapper;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestMovimentacaoBancaria extends RestModel<MovimentacaoBancaria> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "data")
	@JsonFormat(pattern = Constantes.PATTERN_DATA)
	private LocalDate data;

	@JsonProperty(value = "favorecido")
	private String favorecido;

	@JsonProperty(value = "documento")
	private String documento;

	@JsonProperty(value = "valor")
	private BigDecimal valor;

	@JsonProperty(value = "produto")
	private String produto;

	@JsonProperty(value = "emissao")
	@JsonFormat(pattern = Constantes.PATTERN_DATA)
	private LocalDate emissao;

	@JsonProperty(value = "contaOrigem")
	private RestDadosBancarios contaOrigem;
	
	@JsonProperty(value = "contaDestino")
	private RestDadosBancarios contaDestino;

	@JoinColumn(name = "planoContas")
	private RestPlanoContas planoContas;

	@JoinColumn(name = "status")
	private RestStatusMovimentacaoBancaria status;

	@JoinColumn(name = "tipo")
	private RestTipoMovimentacaoBancaria tipo;

	@JoinColumn(name = "usuario")
	private String usuario;

	@Override
	public MovimentacaoBancaria restParaModel()
	{
		return MovimentacaoBancariaMapper.INSTANCE.convertToModel(this);
	}
}
