package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.CapeanteInternacaoMapper;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.model.enums.TipoInternacao;
import com.bmais.gestao.restapi.model.enums.TipoTratamento;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
import com.bmais.gestao.restapi.utility.RuleUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CAPEANTE")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CapeanteInternacao extends Model<RestCapeanteInternacao> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAPEANTE")
	@SequenceGenerator(name = "SEQ_CAPEANTE", sequenceName = "SEQ_CAPEANTE", allocationSize = 1)
	@Column(name = "CAP_ID")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "CAP_RECEM_NASCIDO")
	public Boolean rn = false;
	
	@Column(name = "CAP_NOME_RN")
	public String nomeRn;
	
	@Column(name = "CAP_GEMELAR")
	public Integer gemelar;
	
	@Column(name = "CAP_DATA_NASC_RN")
	public LocalDate dataNascimentoRn;
	
	@Column(name = "CAP_SEXO_RN")
	@Enumerated(EnumType.STRING)
	public Sexo sexoRn;
	
	@Column(name = "CAP_PARCIAL")
	private Boolean parcial = false;
	
	@Column(name = "CAP_PRIMEIRA_PARCIAL")
	private Boolean primeiraParcial = false;
	
	@Column(name = "CAP_PARCIAL_FINAL")
	private Boolean parcialFinal = false;
	
	@Column(name = "CAP_DATA_INICIO_COBRANCA")
	private LocalDate inicioCobranca;
	
	@Column(name = "CAP_DATA_FIM_COBRANCA")
	private LocalDate fimCobranca;
	
	@Column(name = "CAP_COMPLEMENTO")
	private Boolean complemento = false;
	
	@Column(name = "CAP_PACOTE")
	private Boolean pacote = false;
	
	@Column(name = "CAP_DAYCLINIC")
	private Boolean dayclinic = false;

	@Column(name = "CAP_OBSERVACAO")
	private String observacao;
	
	@Column(name = "CAP_TIPO_INTERNACAO")
	@Enumerated(EnumType.STRING)
	private TipoInternacao tipoInternacao;
	
	@Column(name = "CAP_TIPO_TRATAMENTO")
	@Enumerated(EnumType.STRING)
	private TipoTratamento tipoTratamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID_MEDICO", referencedColumnName = "AUD_ID")
	private Auditor medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID_ENFERMEIRO", referencedColumnName = "AUD_ID")
	private Auditor enfermeiro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCA_ID")
	private StatusCapeante status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid_id")
	private CID cid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID", updatable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "CIN_ID")
	private ProntuarioCapeante prontuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "capeante")
	private Collection<ProcedimentoCapeante> procedimentos = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "capeante")
	private Resumo resumo;
	
	public void setResumo(Resumo resumo)
	{
		resumo.setCapeante(this);
		this.resumo = resumo;
	}

	@PrePersist
	private void prePersist() 
	{
		this.status = new StatusCapeante(StatusCapeante.ATIVO);
		this.usuario = new Usuario(RuleUtil.getUsuarioId());
	}

	@Override
	public RestCapeanteInternacao modelParaRest()
	{
		return CapeanteInternacaoMapper.INSTANCE.convertModelToRest(this);
	}
	
	public void addProcedimentos(ProcedimentoCapeante procedimento)
	{
		if(this.procedimentos == null)
		{
			this.procedimentos = new ArrayList<>();
		}
		
		procedimento.setCapeante(this);
		this.procedimentos.add(procedimento);
	}
	
	public boolean ehAlta()
	{
		return this.prontuario.getDataAlta() != null;
	}
}
