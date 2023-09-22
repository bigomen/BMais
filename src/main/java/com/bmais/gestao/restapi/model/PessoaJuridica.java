package com.bmais.gestao.restapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import com.bmais.gestao.restapi.restmodel.RestPessoaJuridica;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PESSOA_JURIDICA")
@Data
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TPJ_ID", discriminatorType = DiscriminatorType.INTEGER)
@Where(clause = "SPJ_ID <> 3")
public abstract class PessoaJuridica<R extends RestPessoaJuridica> extends Model<R>
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOA_JURIDICA")
	@SequenceGenerator(name = "SEQ_PESSOA_JURIDICA", sequenceName = "SEQ_PESSOA_JURIDICA", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "PEJ_ID")
	protected Long id;
	
	@Column(name = "PEJ_RAZAO_SOCIAL")
	protected String razaoSocial;
	
	@Column(name = "PEJ_CNPJ")
	protected String cnpj;
	
	@Column(name = "PEJ_IE")
	protected String ie;

	@Column(name = "PEJ_IM")
	protected String im;
	
	@Column(name = "PEJ_EMAIL")
	protected String email;
	
	@Column(name = "PEJ_TELEFONE")
	protected String telefone;
	
	@Column(name = "PEJ_DT_INICIO")
	protected LocalDate dataInicio;
	
	@Column(name = "PEJ_DT_FIM")
	protected LocalDate dataFim;
	
	@Column(name = "PEJ_DT_INCLUSAO", updatable = false)
	protected LocalDate dataInclusao;
	
    @Column(name = "PEJ_PIS")
    private BigDecimal pis = BigDecimal.ZERO;

    @Column(name = "PEJ_IR")
    private BigDecimal ir = BigDecimal.ZERO;

    @Column(name = "PEJ_COFINS")
    private BigDecimal cofins = BigDecimal.ZERO;

    @Column(name = "PEJ_CSLL")
    private BigDecimal csll = BigDecimal.ZERO;

    @Column(name = "PEJ_GPS")
    private BigDecimal gps;

    @Column(name = "PEJ_ISS_NF")
    private BigDecimal iss;

    @Column(name = "PEJ_OUTROS_IMPOSTOS")
    private BigDecimal outrosImpostos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SPJ_ID")
	protected StatusPessoaJuridica status;

	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	protected StatusPessoaJuridica statusAnterior;

	@Column(name = "PEJ_OBSERVACAO")
	protected String observacao;

	@OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected Collection<DadosBancarios> dadosBancarios = new ArrayList<DadosBancarios>();

	@OneToMany(mappedBy = "pessoaJuridica",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected Collection<Documento> documentos = new ArrayList<Documento>();

	@OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Collection<Contato> contatos = new ArrayList<Contato>();

	public void ativar()
	{
		this.status = new StatusPessoaJuridica(StatusPessoaJuridica.ATIVO);
	}

	public void inativar()
	{
		this.status = new StatusPessoaJuridica(StatusPessoaJuridica.INATIVO);
	}

	public void excluir()
	{
		this.status = new StatusPessoaJuridica(StatusPessoaJuridica.EXCLUIDO);
	}

	@PrePersist
	private void preInclusao()
	{
		this.status = new StatusPessoaJuridica(StatusPessoaJuridica.ATIVO);
	}

	@PreUpdate
	private void preUpdate()
	{
		if(this.dataFim != null)
		{
			if(UtilData.dataMenorIgualADataAtual(this.dataFim)){
				this.status = new StatusPessoaJuridica(StatusPessoaJuridica.INATIVO);
			}else {
				this.status = new StatusPessoaJuridica(StatusPessoaJuridica.ATIVO);
			}
		}else{
			if(this.statusAnterior.getId() == StatusPessoaJuridica.INATIVO){
				this.status = new StatusPessoaJuridica(StatusPessoaJuridica.ATIVO);
			}else {
				this.status = this.statusAnterior;
			}
		}
	}

	@PostLoad
	private void postLoad()
	{
		this.statusAnterior = this.status;
	}

	public void addDadosBancarios(DadosBancarios dadosBancarios)
	{
		if(this.dadosBancarios == null)
		{
			this.dadosBancarios = new ArrayList<>();
		}

		dadosBancarios.setPessoaJuridica(this);
		this.dadosBancarios.add(dadosBancarios);
	}

	public void addDocumentos(Documento documento)
	{
		if(this.documentos == null)
		{
			this.documentos = new ArrayList<>();
		}

		documento.setPessoaJuridica(this);
		documento.setDescricao(documento.getId() != null ? documento.getDescricao() : documento.getTipo() + "_" + this.cnpj + "_");
		this.documentos.add(documento);
	}

	public void addContatos(Contato contato)
	{
		if(this.contatos == null)
		{
			this.contatos = new ArrayList<>();
		}

		contato.setPessoaJuridica(this);
		this.contatos.add(contato);
	}
}
