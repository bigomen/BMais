package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.bmais.gestao.restapi.mapper.TipoMovimentacaoBancariaMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoMovimentacaoBancaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "TIPO_MOVIMENTACAO_BANCARIA")
@Data
@Immutable
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class TipoMovimentacaoBancaria extends Model<RestTipoMovimentacaoBancaria> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Long CREDITO = 1L;
	public static final Long DEBITO = 2L;
	public static final Long APLICACAO = 3L;
	public static final Long RESGATE = 4L;
	public static final Long MOVIMENTACAO_ENTRE_CONTAS = 5L;

	@Id
	@Column(name = "TMB_ID")
	@NonNull
	@Include
	private Long id;
	
	@Column(name = "TMB_DESCRICAO")
	private String descricao;

	@Override
	public RestTipoMovimentacaoBancaria modelParaRest()
	{
		return TipoMovimentacaoBancariaMapper.INSTANCE.convertToRest(this);
	}
}
