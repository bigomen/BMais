package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.VisitaMapper;
import com.bmais.gestao.restapi.model.enums.TipoInternacao;
import com.bmais.gestao.restapi.model.enums.TipoTratamento;
import com.bmais.gestao.restapi.restmodel.RestVisita;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "VISITA")
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Visita extends Model<RestVisita> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VISITA")
	@SequenceGenerator(name = "SEQ_VISITA", sequenceName = "SEQ_VISITA", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "VIS_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "INT_ID", updatable = false)
	@ToString.Exclude
	private Internacao internacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SVI_ID")
	private StatusVisita status;

	//SOLICITAÇÃO DE AUDITORIA
	@Column(name = "VIS_DATA", updatable = false)
	private LocalDate data;

	@Column(name = "VIS_OBSERVACAO")
	private String observacao;

	//DADOS CLÍNICOS DO PACIENTE
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "VISITA_AUDITOR",
			joinColumns = @JoinColumn(name = "VIS_ID"),
			inverseJoinColumns = @JoinColumn(name = "AUD_ID"))
	@ToString.Exclude
	private Collection<Auditor> auditor;

	@Column(name = "VIS_TP_INTERNACAO")
	@Enumerated(EnumType.STRING)
	private TipoInternacao tipoInternacao;

	@Column(name = "VIS_TP_TRATAMENTO")
	@Enumerated(EnumType.STRING)
	private TipoTratamento tipoTratamento;

	@OneToMany(mappedBy = "visita", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Collection<AcomodacaoVisita> acomodacoes;

	@Column(name = "VIS_ACO_ISOLAMENTO")
	private Boolean isolamento;

	@Column(name = "VIS_ACO_ISOLAMENTO_DH_INICIO")
	private LocalDateTime dataInicioIsolamento;

	@Column(name = "VIS_ACO_ISOLAMENTO_DH_FIM")
	private LocalDateTime dataFimIsolamento;

	@Column(name = "VIS_ACO_APARTAMENTO")
	private Boolean apartamento;

	@Column(name = "VIS_ACO_APARTAMENTO_DH_INICIO")
	private LocalDateTime dataInicioApartamento;

	@Column(name = "VIS_ACO_APARTAMENTO_DH_FIM")
	private LocalDateTime dataFimApartamento;

	@Column(name = "VIS_ACO_ENFERMARIA")
	private Boolean enfermaria;

	@Column(name = "VIS_ACO_ENFERMARIA_DH_INICIO")
	private LocalDateTime dataInicioEnfermaria;

	@Column(name = "VIS_ACO_ENFERMARIA_DH_FIM")
	private LocalDateTime dataFimEnfermaria;

	@Column(name = "VIS_ACO_SEMI")
	private Boolean semi;

	@Column(name = "VIS_ACO_SEMI_DH_INICIO")
	private LocalDateTime dataInicioSemi;

	@Column(name = "VIS_ACO_SEMI_DH_FIM")
	private LocalDateTime dataFimSemi;

	@Column(name = "VIS_ACO_UTI")
	private Boolean uti;

	@Column(name = "VIS_ACO_UTI_DH_INICIO")
	private LocalDateTime dataInicioUti;

	@Column(name = "VIS_ACO_UTI_DH_FIM")
	private LocalDateTime dataFimUti;

	@Column(name = "VIS_ACO_BERCARIO")
	private Boolean bercario;

	@Column(name = "VIS_ACO_BERCARIO_DH_INICIO")
	private LocalDateTime dataInicioBercario;

	@Column(name = "VIS_ACO_BERCARIO_DH_FIM")
	private LocalDateTime dataFimBercario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CID_ID_PRINCIPAL", referencedColumnName = "CID_ID")
	@ToString.Exclude
	private CID cidPrincipal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CID_ID_SECUNDARIO", referencedColumnName = "CID_ID")
	@ToString.Exclude
	private CID cidSecundario;

	@Column(name = "VIS_CONDICOES_SOCIAIS")
	private String condicoesSociais;

	//COMPONENTES DE ALTO CUSTO
	@OneToMany(mappedBy = "visita", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Collection<ComponenteAltoCusto> componentesAltoCusto;

	//NEGOCIAÇÃO DIRETA
	@Column(name = "VIS_GLOSA_DIARIA_QUANTIDADE")
	private Long glosaDiariaQuantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_GLOSA_TIPO_ACOMODACAO", referencedColumnName = "ACO_ID")
	@ToString.Exclude
	private Acomodacao glosaDiariaAcomodacao;

	@Column(name = "VIS_TROCA_DIARIA_QUANTIDADE_NEGOCIADA")
	private Long trocaDiariaQuantidadeNegociada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_TROCA_DIARIA_DE", referencedColumnName = "ACO_ID")
	@ToString.Exclude
	private Acomodacao trocaDiariaDe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_TROCA_DIARIA_PARA", referencedColumnName = "ACO_ID")
	@ToString.Exclude
	private Acomodacao trocaDiariaPara;

	@Column(name = "VIS_MEDICACAO_NEGADA_QUANTIDADE")
	private Long medicacaoNegadaQuantidade;

	@Column(name = "VIS_MEDICACAO_NEGADA_DESCRICAO")
	private String medicacaoNegadaDescricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_PROCEDIMENTO_NEGADO")
	@ToString.Exclude
	private Tuss procedimentoNegado;

	@Column(name = "VIS_PROCEDIMENTO_NEGADO_QUANTIDADE")
	private Long qtdProcedimentoNegado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_TROCA_PROCEDIMENTO_DE")
	@ToString.Exclude
	private Tuss trocaProcedimentoDe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_TROCA_PROCEDIMENTO_PARA")
	@ToString.Exclude
	private Tuss trocaProcedimentoPara;


	//HOME CARE
	@Column(name = "VIS_INDICACAO_HC")
	private Boolean indicacaoHC;

	@OneToMany(mappedBy = "visita", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Collection<Curativo> curativos;

	@Column(name = "VIS_OXIGENIOTERAPIA_INTERMITENTE")
	private boolean oxigenioterapiaIntermitente = false;

	@Column(name = "VIS_OXIGENIOTERAPIA_CONTINUO")
	private boolean oxigenioterapiaContinuo = false;

	@Column(name = "VIS_OXIGENIOTERAPIA_VENTILACAO")
	private boolean oxigenioterapiaVentilacao = false;

	@Column(name = "VIS_MOBILIZACAO")
	private String mobilizacao;

	@Column(name = "VIS_NIVEL_CONCIENCIA")
	private String nivelConciencia;

	@Column(name = "VIS_ALIMENTACAO_ORAL")
	private Boolean alimentacaoOral;

	@Column(name = "VIS_ALIMENTACAO_SONDA")
	private Boolean alimentacaoSonda;

	@Column(name = "VIS_ALIMENTACAO_PARENTERAL")
	private Boolean alimentacaoParenteral;

	@Column(name = "VIS_ALIMENTACAO_OSTOMIA")
	private Boolean alimentacaoOstomia;

	@Column(name = "VIS_OSTOMIA_GASTROSTOMIA")
	private Boolean ostomiaGastromia;

	@Column(name = "VIS_OSTOMIA_COLOSTOMIA")
	private Boolean ostomiaColostomia;

	@Column(name = "VIS_OSTOMIA_JEJUNOSTOMIA")
	private Boolean ostomiaJejunostomia;

	@Column(name = "VIS_ACESSO_VENOSO")
	private String acessoVenoso;

	@Column(name = "VIS_TRAQUESOSTOMIA")
	private String traquesostomia;

	@Column(name = "VIS_ASPIRACAO_FREQUENCIA")
	private Long aspiracaoFrequencia;

	//PRORROGACAO
	@Column(name = "VIS_PRORROGACAO_ATE")
	private LocalDateTime prorrogacaoAte;

	@Column(name = "VIS_OBS_PRORROGACAO")
	private String obsProrrogacao;

	//AVALIAÇÃO DE RELATÓRIO
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RELATORIO_VISITA",
			joinColumns = @JoinColumn(name = "VIS_ID"),
			inverseJoinColumns = @JoinColumn(name = "REL_ID"))
	@ToString.Exclude
	private Set<AvaliacaoRelatorio> avaliacaoRelatorio;

	public void addCurativos(Curativo curativo)
	{
		if(this.curativos == null)
		{
			this.curativos = new ArrayList<>();
		}

		curativo.setVisita(this);
		this.curativos.add(curativo);
	}

	public void addComponentesAltoCusto(ComponenteAltoCusto componenteAltoCusto)
	{
		if(this.componentesAltoCusto == null)
		{
			this.componentesAltoCusto = new ArrayList<>();
		}

		componenteAltoCusto.setVisita(this);
		this.componentesAltoCusto.add(componenteAltoCusto);
	}

	public Visita(Long idVisita, Long idInternacao, String senhaConvenio, StatusInternacao status, Long idPaciente, String nomePaciente, LocalDate dataNascimento,
				  String matriculaPaciente, Long idCliente, String razaoCliente, LocalDate data){
		this.id = idVisita;
		this.data = data;
		Cliente cliente = new Cliente(idCliente, razaoCliente);
		Paciente paciente = new Paciente(idPaciente, nomePaciente, dataNascimento, matriculaPaciente, cliente);
		this.internacao = new Internacao(idInternacao, senhaConvenio, status, paciente);
	}
	
	public Visita(Long idVisita, LocalDate data, String obs, String statusVisita,
					Long idInternacao, LocalDateTime dataInternacao, LocalDateTime dataAlta, String senhaConvenio, Long idStatusInternacao, String statusInternacao,
					String nomeHospital, 
					Long idPaciente, String nomePaciente, LocalDate dataNascimento, String matriculaPaciente, 
					Long idCliente, String razaoCliente, Long idAuditor, String nomeAuditor){
		this.id = idVisita;
		this.data = data;
		this.observacao = obs;
		this.status = new StatusVisita(statusVisita);
		Cliente cliente = new Cliente(idCliente, razaoCliente);
		Paciente paciente = new Paciente(idPaciente, nomePaciente, dataNascimento, matriculaPaciente, cliente);
		Hospital hospital = new Hospital();
		hospital.setRazaoSocial(nomeHospital);
		this.internacao = new Internacao(idInternacao, senhaConvenio, new StatusInternacao(idStatusInternacao, statusInternacao), paciente);
		this.internacao.setDataHora(dataInternacao);
		this.internacao.setDataHoraAlta(dataAlta);
		this.internacao.setHospital(hospital);
		this.auditor = Set.of(new Auditor(idAuditor, nomeAuditor));
	}
	
	@Override
	public RestVisita modelParaRest(){
		return VisitaMapper.INSTANCE.convertToRest(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Visita visita = (Visita) o;
		return getId() != null && Objects.equals(getId(), visita.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
