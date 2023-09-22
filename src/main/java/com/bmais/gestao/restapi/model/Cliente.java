package com.bmais.gestao.restapi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.ClienteMapper;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@DiscriminatorValue(value = "1")
public class Cliente extends PessoaJuridica<RestCliente>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PASTA_DOCUMENTOS = "CLIENTES";

	@Column(name = "PEJ_CNPJ_DIFRENTE_CLIENTE")
	private Boolean cnpjDiferente = false;

	@Column(name = "PEJ_CNPJ_FINANCEIRO_CLIENTE")
	private String cnpjFinanceiro;

	@Column(name = "PEJ_CAPEANTE_PROPRIO_CLIENTE")
	private Boolean capeante = false;

	@Column(name = "PEJ_VALOR_ALTO_CUSTO_CLIENTE")
	private BigDecimal valoraltoCusto;

	@Column(name = "PEJ_SIGLA_CLIENTE")
	private String sigla;

	@Column(name = "PEJ_DH_PROCEDIMENTO_CAPEANTE")
	private Boolean dhProcedimentoCapeante = false;

	@Column(name = "PEJ_PERC_PROCEDIMENTO_CAPEANTE")
	private Boolean percProcedimentoCapeante = false;

	@Column(name = "PEJ_DAY_CLINIC_CAPEANTE")
	private Boolean dayClinicCapeante = false;

	@Column(name = "PEJ_COMPLEMENTO_CAPEANTE")
	private Boolean complementoCapeante = false;

	@Column(name = "PEJ_PACOTE_CAPEANTE")
	private Boolean pacoteCapeante = false;

	@Column(name = "PEJ_ABRIR_DIARIAS_CAPEANTE")
	private Boolean abrirDiariasCapeante = false;

	@Column(name = "PEJ_PRORROGACAO")
	private Boolean prorrogacao = false;

	@Column(name = "PEJ_LOGOTIPO")
	private String logotipo;
	
	@Column(name = "PEJ_ADMINISTRATIVO")
	private Boolean administrativo = false;
	
	@Column(name = "PEJ_ASSINATURA_AUDITORES")
	private Boolean assinaturaAuditores = false;
	
	@Column(name = "PEJ_FREQUENCIA_VISITA")
	private Integer frequenciaVisita;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
	private Collection<Usuario> usuarios = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
	private Collection<HospitalCliente> hospitais = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
	private Collection<ClienteServico> servicos = new ArrayList<>();

	public void addServicos(ClienteServico servico)
	{
		if(this.servicos == null)
		{
			this.servicos = new ArrayList<>();
		}

		servico.setCliente(this);
		this.servicos.add(servico);
	}
	public void addHospitais(HospitalCliente hospitalCliente)
	{
		if(this.hospitais == null)
		{
			this.hospitais = new ArrayList<>();
		}

		hospitalCliente.setCliente(this);
		this.hospitais.add(hospitalCliente);
	}
	public void addUsuarios(Usuario usuario)
	{
		if(this.usuarios == null)
		{
			this.usuarios = new ArrayList<>();
		}

		usuario.setCliente(this);
		this.usuarios.add(usuario);
	}

//	@PostLoad
//	private void postLoad()
//	{
//		this.statusAnterior = this.status;
//	}
//
//	@PreUpdate
//	private void preUpdate()
//	{
//		this.status = this.statusAnterior;
//	}

	public Cliente(Long idCliente, String razaoSocial, String cnpj, BigDecimal valorAltoCusto, String status) {
		this.setId(idCliente);
		this.setRazaoSocial(razaoSocial);
		this.setCnpj(cnpj);
		this.valoraltoCusto = valorAltoCusto;
		StatusPessoaJuridica statusCliente = new StatusPessoaJuridica(status);
		this.setStatus(statusCliente);
	}

	public Cliente(Long id, String razaoSocial){
		this.setId(id);
		this.setRazaoSocial(razaoSocial);
	}

	public Cliente(Long id){
		this.setId(id);
	}

	@Override
	public RestCliente modelParaRest()
	{
		return ClienteMapper.INSTANCE.convertToRest(this);
	}

	public RestCliente modelParaFullRest()
	{
		return ClienteMapper.INSTANCE.convertToFullCliente(this);
	}
}
