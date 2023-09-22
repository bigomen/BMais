package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.MovimentacaoBancariaMapper;
import com.bmais.gestao.restapi.restmodel.RestMovimentacaoBancaria;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MOVIMENTACAO_BANCARIA")
@DynamicUpdate
@DynamicInsert
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class MovimentacaoBancaria extends Model<RestMovimentacaoBancaria> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIMENTACAO_BANCARIA")
	@SequenceGenerator(name = "SEQ_MOVIMENTACAO_BANCARIA", sequenceName = "SEQ_MOVIMENTACAO_BANCARIA", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "MBA_ID")
	private Long id;
	
	@Column(name = "MBA_DATA", updatable = false)
	@Setter(value = AccessLevel.NONE)
	private LocalDate data;
	
	@Column(name = "MBA_FAVORECIDO")
	private String favorecido;
	
	@Column(name = "MBA_DOCUMENTO")
	private String documento;
	
	@Column(name = "MBA_VALOR")
	private BigDecimal valor;
	
	@Column(name = "MBA_PRODUTO")
	private String produto;
	
	@Column(name = "MBA_EMISSAO")
	private LocalDate emissao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DAB_ID_CONTA_ORIGEM", referencedColumnName = "DAB_ID")
	private DadosBancarios contaOrigem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DAB_ID_CONTA_DESTINO", referencedColumnName = "DAB_ID")
	private DadosBancarios contaDestino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PCO_ID")
	private PlanoContas planoContas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SMB_ID")
	private StatusMovimentacaoBancaria status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TMB_ID")
	private TipoMovimentacaoBancaria tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID")
	@Setter(value = AccessLevel.NONE)
	private Usuario usuario;
	
	public boolean ehMovimentacaoEntreContas()
	{
		return this.tipo.equals(new TipoMovimentacaoBancaria(TipoMovimentacaoBancaria.MOVIMENTACAO_ENTRE_CONTAS));
	}
	
	@PrePersist
	private void prePersist()
	{
		this.usuario = new Usuario(RuleUtil.getUsuarioId());
		this.data = UtilData.obterDataAtual();
	}

	@Override
	public RestMovimentacaoBancaria modelParaRest()
	{
		return MovimentacaoBancariaMapper.INSTANCE.convertToRest(this);
	}
}