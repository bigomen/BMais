package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AuditorMapper;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.restmodel.RestAuditor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "AUDITOR")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Auditor extends Model<RestAuditor> implements Serializable
{
	public static final String PASTA_DOCUMENTOS = "AUDITORES";
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUDITOR")
	@SequenceGenerator(name = "SEQ_AUDITOR", sequenceName = "SEQ_AUDITOR", allocationSize = 1)
	@Column(name = "AUD_ID")
	@Include
	private Long id;
	
	@Column(name = "AUD_NOME")
	private String nome;
	
	@Column(name = "AUD_DT_NASCIMENTO")
	private LocalDate dataNascimento;

	@Column(name = "AUD_CRM_COREM")
	private String crmCorem;

	@Column(name = "AUD_CPF")
	private String cpf;

	@Column(name = "AUD_RG")
	private String rg;

	@Column(name = "AUD_EMISSOR")
	private String emissor;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TPA_ID")
	private TipoAuditor tipoAuditor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private Prestador prestador;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUD_SEXO")
	private Sexo sexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAU_ID")
	private StatusAuditor status;

	@Column(name = "AUD_EMAIL")
	private String email;

	@Column(name = "AUD_TELEFONE")
	private String telefone;

	@Column(name = "AUD_ASSINATURA")
	private String assinatura;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "auditor")
	private Collection<Vinculo> vinculos = new ArrayList<>();

	@Transient
	private Collection<Cobertura> coberturas = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "AUD_ID")
	private Collection<Documento> documentos = new ArrayList<Documento>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID")
	private Usuario usuario;

	public Auditor(Long id, String nome, Long idTipo, String descTipo)
	{
		this.id = id;
		this.nome = nome;
		this.tipoAuditor = new TipoAuditor(idTipo, descTipo);
	}
	public Auditor(Long id)
	{
		this.id = id;
	}

	public Auditor(Long id, String nome, String documento, TipoAuditor tipo, StatusAuditor status)
	{
		this.id = id;
		this.nome = nome;
		this.crmCorem = documento;
		this.tipoAuditor = tipo;
		this.status = status;
	}

	public Auditor(Long id, String nome, TipoAuditor tipoAuditor){
		this.setId(id);
		this.setNome(nome);
		this.setTipoAuditor(tipoAuditor);
	}

	public Auditor(Long id, String nome){
		this.setId(id);
		this.setNome(nome);
	}

	public Auditor(Long id, String nome, TipoAuditor tipoAuditor, String corem, String email, Long idUsuario, String emailUsuario, StatusAuditor status){
		this.id = id;
		this.nome = nome;
		this.tipoAuditor = tipoAuditor;
		this.crmCorem = corem;
		this.email = email;
		this.status = status;
		if(idUsuario != null){
			this.usuario = new Usuario(idUsuario, emailUsuario);
		}
	}

	@Override
	public RestAuditor modelParaRest() {
		return AuditorMapper.INSTANCE.convertToRest(this);
	}

	public RestAuditor modelParaRestVisita(){
		return AuditorMapper.INSTANCE.convertToSimpleRest(this);
	}

	public void addDocumentos(Documento documento)
	{
		if(this.documentos == null)
		{
			this.documentos = new ArrayList<>();
		}

		documento.setAuditor(this);
		documento.setDescricao(documento.getId() != null ? documento.getDescricao() : documento.getTipo() + "_" + this.crmCorem + "_");
		this.documentos.add(documento);
	}
	
	public void addVinculos(Vinculo vinculo)
	{
		if(this.vinculos == null)
		{
			this.vinculos = new ArrayList<>();
		}
		
		vinculo.setAuditor(this);
		this.vinculos.add(vinculo);
	}
}
