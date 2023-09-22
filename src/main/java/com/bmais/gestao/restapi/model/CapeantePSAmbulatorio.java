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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.CapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.model.enums.TipoCapeante;
import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.utility.RuleUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CAPEANTE_PS_AMBULATORIO")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class CapeantePSAmbulatorio extends Model<RestCapeantePSAmbulatorio> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAPEANTE_PS_AMBULATORIO")
	@SequenceGenerator(name = "SEQ_CAPEANTE_PS_AMBULATORIO", sequenceName = "SEQ_CAPEANTE_PS_AMBULATORIO", allocationSize = 1)
	@Column(name = "CPA_ID")
	private Long id;

	@Column(name = "CPA_TIPO")
	@Enumerated(EnumType.STRING)
	private TipoCapeante tipoCapeante;

	@Column(name = "CPA_LOTE_PROTOCOLO")
	private String loteProtocolo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCA_ID")
	private StatusCapeante status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOS_ID")
	private Hospital hospital;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private Cliente cliente;

	@Column(name = "CPA_DATA_ABERTURA")
	private LocalDate dataAbertura;

	@Column(name = "CPA_DATA_FECHAMENTO")
	private LocalDate dataFechamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID_MEDICO", referencedColumnName = "AUD_ID")
	private Auditor auditorMedico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID_ENFERMEIRO", referencedColumnName = "AUD_ID")
	private Auditor auditorEnfermeiro;

	@Column(name = "CPA_OBSERVACAO")
	private String observacao;

	@OneToMany(mappedBy = "capeantePSAmbulatorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ResumoCapeantePSAmbulatorio> resumo = new ArrayList<>();
	
	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	protected StatusCapeante statusAnterior;
	
	public void addResumo(ResumoCapeantePSAmbulatorio resumo)
	{
		if(this.resumo == null)
		{
			this.resumo = new ArrayList<>();
		}
		
		resumo.setCapeantePSAmbulatorio(this);
		this.resumo.add(resumo);
	}

	public CapeantePSAmbulatorio(Long idCapeante, TipoCapeante tipoCapeante, String loteProtocolo, StatusCapeante status, Long idUsuario, String emailUsuario,
								 Long idHospital, String razaoHospital, Long idCliente, String razaoCliente, LocalDate dataAbertura, LocalDate dataFechamento,
								 Long idAuditorMedico, String nomeAuditorMedico, Long idAuditorEnfermeiro, String nomeAuditorEnfermeiro, String observacao){
		this.id = idCapeante;
		this.tipoCapeante = tipoCapeante;
		this.loteProtocolo = loteProtocolo;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.observacao = observacao;
		this.usuario = new Usuario(idUsuario, emailUsuario);
		this.hospital = new Hospital(idHospital, razaoHospital);
		this.cliente = new Cliente(idCliente, razaoCliente);
		this.auditorMedico = new Auditor(idAuditorMedico, nomeAuditorMedico);
		this.auditorEnfermeiro = new Auditor(idAuditorEnfermeiro, nomeAuditorEnfermeiro);
	}

	@PrePersist
	private void preInclusao(){
		this.usuario = new Usuario(RuleUtil.getUsuarioId());
	}

	@PreUpdate
	private void preAtualizacao(){
		this.usuario = new Usuario(RuleUtil.getUsuarioId());
	}

	@Override
	public RestCapeantePSAmbulatorio modelParaRest(){
		return CapeantePSAmbulatorioMapper.INSTANCE.convertToRest(this);
	}
}
