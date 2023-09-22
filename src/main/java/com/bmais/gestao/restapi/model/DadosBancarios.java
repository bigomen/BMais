package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.bmais.gestao.restapi.mapper.DadosBancariosMapper;
import com.bmais.gestao.restapi.model.enums.TipoContaBancaria;
import com.bmais.gestao.restapi.restmodel.RestDadosBancarios;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DADO_BANCARIO")
@DynamicUpdate
@DynamicInsert
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class DadosBancarios extends Model<RestDadosBancarios> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DADOS_BANCARIOS")
	@SequenceGenerator(name = "SEQ_DADOS_BANCARIOS", sequenceName = "SEQ_DADO_BANCARIO", allocationSize = 1)
	@Column(name = "DAB_ID")
	@Include
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BAN_ID", nullable = false)
	private Banco banco;
	
	@Column(name = "DAB_AGENCIA", nullable = false)
	private String agencia;
	
	@Column(name = "DAB_NUM_CONTA", nullable = false)
	private String conta;

	@Enumerated(EnumType.STRING)
	@Column(name = "DAB_TP_CONTA_BANCARIA")
	private TipoContaBancaria tipoContaBancaria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID", updatable = false)
	private PessoaJuridica pessoaJuridica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID", updatable = false)
	private Colaborador colaborador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", updatable = false)
	private Empresa empresa;

	@Column(name = "DAB_ATIVO")
	private boolean ativo;

	@ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@Column(name = "DAB_PROXIMO_CHEQUE")
	private Integer proximoCheque;

	@Column(name = "DAB_GERENTE")
	private String gerente;

	@Column(name = "DAB_LIMITE_CREDITO")
	private BigDecimal limiteCredito;

	@Column(name = "DAB_TELEFONE")
	private String telefone;

	@Column(name = "DAB_SITE")
	private String siteBanco;

	@Column(name = "DAB_SALDO")
	private BigDecimal saldo;
	
	@PrePersist
	private void init()
	{
		this.ativo = true;
	}
	
	public DadosBancarios(Long id)
	{
		super();
		this.id = id;
	}
	
	public DadosBancarios(Long idDadosBancarios, Long idBanco, String nomeBanco, String agencia, String conta){
		this.setId(idDadosBancarios);
		Banco banco = new Banco();
		banco.setId(idBanco);
		banco.setNome(nomeBanco);
		this.setBanco(banco);
		this.setAgencia(agencia);
		this.setConta(conta);
	}

	public DadosBancarios(Long id, Banco banco, String agencia, String conta){
		this.setId(id);
		this.setBanco(banco);
		this.setAgencia(agencia);
		this.setConta(conta);
	}

	public DadosBancarios(Long id, Long idBanco, String nomeBanco, String agencia, String conta, TipoContaBancaria tipoContaBancaria) {
		this.id = id;
		this.agencia = agencia;
		this.conta = conta;
		this.tipoContaBancaria = tipoContaBancaria;
		this.setBanco(new Banco(idBanco, nomeBanco));
	}

	public DadosBancarios(Long id, Long idBanco, String codigoBanco, String nomeBanco, String agencia, String conta, boolean status, BigDecimal saldo)
	{
		this.id = id;
		this.agencia = agencia;
		this.conta = conta;
		this.ativo = status;
		this.saldo = saldo;
		this.setBanco(new Banco(idBanco, codigoBanco, nomeBanco));
	}

	@Override
	public RestDadosBancarios modelParaRest() {
		return DadosBancariosMapper.INSTANCE.convertToRest(this);
	}
}
