package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ColaboradorMapper;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.restmodel.RestColaborador;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "COLABORADOR")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Colaborador extends Model<RestColaborador> implements Serializable
{
    public static final String PASTA_DOCUMENTOS = "COLABORADORES";
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLABORADOR")
	@SequenceGenerator(name = "SEQ_COLABORADOR", sequenceName = "SEQ_COLABORADOR", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "COL_ID")
	private Long id;

	@Column(name = "COL_NOME")
	private String nome;

	@Column(name = "COL_FOTO")
	private String foto;

	@Column(name = "COL_DT_NASCIMENTO")
	private LocalDate dataNascimento;

	@Column(name = "COL_TELEFONE")
	private String telefone;

	@Column(name = "COL_CELULAR")
	private String celular;

	@Column(name = "COL_EMAIL")
	private String email;

	@Column(name = "COL_DT_ADMISSAO")
	private LocalDate dataAdmissao;

	@Column(name = "COL_DT_DEMISSAO")
	private LocalDate dataDemissao;

	@Column(name = "COL_DH_INCLUSAO", updatable = false)
	private LocalDate dataInclusao;

	@Column(name = "COL_DH_ATUALIZACAO")
	private LocalDate dataAtualizacao;

	@ManyToOne
	@JoinColumn(name = "STC_ID")
	private StatusColaborador status;

	@Column(name = "COL_CPF")
	private String cpf;

	@Column(name = "COL_RG_RNE")
	private String rgRne;

	@Column(name = "COL_EMISSOR")
	private String emissor;

	@Column(name = "COL_CTPS_NUMERO")
	private String ctpsNumero;

	@Column(name = "COL_CTPS_SERIE")
	private String ctpsSerie;

	@ManyToOne
	@JoinColumn(name = "UF_ID_CTPS", referencedColumnName = "UF_ID")
	private UF ctpsUf;

	@Column(name = "COL_PIS_PASEP")
	private String pisPasep;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "ESC_ID")
	private EstadoCivil estadoCivil;

	@Enumerated(EnumType.STRING)
	@Column(name = "COL_SEXO")
	private Sexo sexo;

	@Column(name = "COL_DT_EXPEDICAO_RG")
	private LocalDate dataExpedicaoRG;

	@Column(name = "COL_SUS")
	private String sus;

	@Column(name = "COL_RESERVISTA")
	private String reservista;

	@Column(name = "COL_NACIONALIDADE")
	private String nacionalidade;

	@Column(name = "COL_LOCAL_NASCIMENTO")
	private String localNascimento;

	@ManyToOne
	@JoinColumn(name = "UF_ID")
	private UF uf;

	@Column(name = "COL_NOME_PAI")
	private String nomePai;

	@Column(name = "COL_NOME_MAE")
	private String nomeMae;

	@ManyToOne
	@JoinColumn(name = "ESO_ID")
	private Escolaridade escolaridade;

	@Column(name = "COL_CURSANDO")
	private Boolean cursando;

	@Column(name = "COL_PCD")
	private Boolean pcd;

	@Column(name = "COL_NOME_EMERGENCIA")
	private String nomeEmergencia;

	@Column(name = "COL_PARENTESCO_EMERGENCIA")
	private String parentescoEmergencia;

	@Column(name = "COL_ENDERECO_EMERGENCIA")
	private String enderecoEmergencia;

	@Column(name = "COL_TELEFONE_EMERGENCIA")
	private String telefoneEmergencia;

	@Column(name = "COL_CELULAR_EMERGENCIA")
	private String celularEmergencia;

	@Column(name = "COL_TEL_COMERCIAL_EMERGENCIA")
	private String telComercialEmergencia;

	@Column(name = "COL_TITULO_ELEITOR")
	private String tituloEleitor;

	@Column(name = "COL_INSTITUICAO_ENSINO")
	private String instituicaoEnsino;

	@Column(name = "COL_CURSO")
	private String curso;

	@Column(name = "COL_OBSERVACOES")
	private String observacoes;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@Column(name = "COL_SALARIO_BRUTO")
	private BigDecimal salarioBruto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private Cliente cliente;

	@Column(name = "COL_VALE_TRANSPORTE")
	private Boolean valeTransporte;

	@ManyToOne
	@JoinColumn(name = "CAR_ID")
	private Cargo cargo;

	@Column(name = "COL_REDE_SOCIAL")
	private String redeSocial;

	@ManyToOne
	@JoinColumn(name = "MUN_ID")
	private Municipio municipio;

	@ManyToOne
	@JoinColumn(name = "MDE_ID")
	private MotivoDemissao motivoDemissao;

	@Column(name = "COL_MENOR")
	private Boolean menor;

	@Column(name = "COL_RESPONSAVEL")
	private String responsavel;

	@Column(name = "COL_RESPONSAVEL_CPF")
	private String responsavelCpf;

	@Column(name = "COL_HORARIO_SEG_QUI_INICIO")
	private LocalTime horarioSegQuiInicio;

	@Column(name = "COL_HORARIO_SEG_QUI_FIM")
	private LocalTime horarioSegQuiFim;

	@Column(name = "COL_HORARIO_SEXTA_INICIO")
	private LocalTime horarioSextaInicio;

	@Column(name = "COL_HORARIO_SEXTA_FIM")
	private LocalTime horaioSextaFim;

	@Column(name = "COL_HORARIO_ALMOCO_INICIO")
	private LocalTime horarioAlmocoInicio;

	@Column(name = "COL_HORARIO_ALMOCO_FIM")
	private LocalTime horarioAlmocoFim;

	@Column(name = "COL_OPERACIONAL")
	private Boolean operacional;

	@Column(name = "COL_PENDENCIAS")
	private Boolean pendencias;
	
	@Transient
	private Integer idade;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Dependente> dependentes;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Documento> documentos;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Cipa> cipas;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<DadosBancarios> dadosBancarios;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<EvolucaoColaborador> evolucoes;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Ferias> ferias;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Afastamento> afastamentos;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ValeTransporte> valesTransporte;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ColaboradorBeneficio> colaboradorBeneficios;

	public Colaborador(Long id){
		this.setId(id);
	}

	public Colaborador(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Colaborador(Long id, String nomeColaborador, Sexo sexo, LocalDate dataNascimento, LocalDate dataAdmissao, Long idEmpresa, String razaoEmpresa, StatusColaborador status, Long idCargo, String descricaoCargo){
		this.setId(id);
		this.setNome(nomeColaborador);
		this.setSexo(sexo);
		this.setEmpresa(new Empresa(idEmpresa, razaoEmpresa));
		this.setEmpresa(empresa);
		this.setStatus(status);
		this.setCargo(new Cargo(idCargo, descricaoCargo));
		this.setDataAdmissao(dataAdmissao);
		this.idade = LocalDate.now().getYear() - dataNascimento.getYear();
	}

	public Colaborador(Long id, String nome, Long idCliente, String nomeCliente, BigDecimal salarioBruto)
	{
		this.id = id;
		this.nome = nome;
		this.salarioBruto = salarioBruto;

		if(idCliente != null)
		{
			Cliente cliente = new Cliente(idCliente, nomeCliente);
			this.cliente = cliente;
		}
	}

	@PrePersist
	private void prePersist(){
		this.dataInclusao = UtilData.obterDataHoraAtual().toLocalDate();
		this.dataAtualizacao = dataInclusao;
	}

	@PreUpdate
	private void preUpdate(){
		this.dataAtualizacao = UtilData.obterDataHoraAtual().toLocalDate();
	}



	@Override
	public RestColaborador modelParaRest() {
		RestColaborador restColaborador = ColaboradorMapper.INSTANCE.convertToRest(this);
		if(this.dadosBancarios != null && !this.dadosBancarios.isEmpty()){
			restColaborador.setDadosBancarios(this.dadosBancarios.stream().findFirst().get().modelParaRest());
		}
		return restColaborador;
	}

	public void addDadosBancarios(DadosBancarios restParaModel) {
		if(this.dadosBancarios == null){
			this.dadosBancarios = new ArrayList<>();
		}
		restParaModel.setColaborador(this);
		this.dadosBancarios.add(restParaModel);
	}

	public void addDocumentos(Documento documento){
		if(this.documentos == null){
			this.documentos = new ArrayList<>();
		}
		documento.setColaborador(this);
		documento.setDescricao(documento.getId() != null ? documento.getDescricao() : documento.getTipo() + "_" + this.cpf + "_");
		this.documentos.add(documento);
	}
}
