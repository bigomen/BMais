package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.InternacaoMapper;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.restmodel.RestInternacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "INTERNACAO")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Internacao extends Model<RestInternacao> implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTERNACAO")
	@SequenceGenerator(name = "SEQ_INTERNACAO", sequenceName = "SEQ_INTERNACAO", allocationSize = 1)
	@Include
	@Column(name = "INT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAC_ID", updatable = false)
	private Paciente paciente;

//	@Column(name = "INT_NUMERO_INTERNACAO")
//	private Long numeroInternacao;

	@Column(name = "INT_DH_INTERNACAO")
	private LocalDateTime dataHora;

	@Column(name = "INT_DH_ALTA")
	private LocalDateTime dataHoraAlta;

	@Column(name = "INT_OBITO")
	private Boolean obito;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOS_ID", updatable = false)
	private Hospital hospital;

	@Column(name = "INT_SENHA_CONVENIO")
	private String senha;

	@Column(name = "INT_PENDENCIA_RELATORIO")
	private Boolean pendenciaRelatorio;

	@ManyToOne
	@JoinColumn(name = "SIN_ID")
	private StatusInternacao status;

	@Column(name = "INT_OBSERVACAO")
	private String observacao;

	@Column(name = "INT_DIAS_LIBERADOS")
	private Integer diasLiberados;

	public Internacao(Long id){
		this.setId(id);
	}

	public Internacao(Long idInternacao, Long idPaciente, Long idCliente, String nomePaciente, LocalDate nascimentoPaciente, String matriculaPaciente,
					  String razaoCliente, LocalDateTime dataInternacao, StatusInternacao status){
		this.setId(idInternacao);
		this.setDataHora(dataInternacao);
		this.setStatus(status);
		Cliente cliente = new Cliente(idCliente, razaoCliente);
		Paciente paciente = new Paciente(idPaciente, nomePaciente, nascimentoPaciente, matriculaPaciente, cliente);
		this.setPaciente(paciente);
	}
	
	public Internacao(Long idInternacao, Long idPaciente, Long idCliente, Long idHospital, String nomePaciente, LocalDate nascimentoPaciente, String matriculaPaciente,
			  String razaoCliente, String nomeHospital, LocalDateTime dataInternacao, StatusInternacao status, 
			  Sexo sexo, LocalDateTime dhAlta, String senha, Integer diasLiberados, Boolean obito){
		this.setId(idInternacao);
		this.setDataHora(dataInternacao);
		this.setStatus(status);
		this.setObito(obito);
		this.setDiasLiberados(diasLiberados);
		this.setDataHoraAlta(dhAlta);
		this.setSenha(senha);
		Cliente cliente = new Cliente(idCliente, razaoCliente);
		Paciente paciente = new Paciente(idPaciente, nomePaciente, nascimentoPaciente, matriculaPaciente, cliente);
		paciente.setSexo(sexo);
		Hospital hospital = new Hospital(idHospital, nomeHospital);
		this.setPaciente(paciente);
		this.setHospital(hospital);
	}

	public Internacao(Long id, String senha, StatusInternacao status, Paciente paciente) {
		this.id = id;
		this.senha = senha;
		this.status = status;
		this.paciente = paciente;
	}

	public Internacao(Long idInternacao, LocalDateTime dataHoraInternacao, LocalDateTime dataHoraAltaInternacao, Long idPaciente,
					  String nomePaciente, LocalDate dataNascimentoPaciente, String matriculaPaciente, Long idCliente,
					  String razaoSocialCliente) {
		this.id = idInternacao;
		this.dataHora = dataHoraInternacao;
		this.dataHoraAlta = dataHoraAltaInternacao;
		this.paciente = new Paciente();
		this.paciente.setId(idPaciente);
		this.paciente.setNome(nomePaciente);
		this.paciente.setDataNascimento(dataNascimentoPaciente);
		this.paciente.setMatricula(matriculaPaciente);
		Cliente cliente = new Cliente();
		cliente.setId(idCliente);
		cliente.setRazaoSocial(razaoSocialCliente);
		this.paciente.setCliente(cliente);
	}
	
	@PrePersist
	private void prePersist()
	{
		this.status = new StatusInternacao(StatusInternacao.ATIVO);
	}

	@Override
	public RestInternacao modelParaRest() {
		return InternacaoMapper.INSTANCE.convertToRest(this);
	}
	
	public RestInternacao modelParaSimpleRest()
	{
		return InternacaoMapper.INSTANCE.convertToSimpleRest(this);
	}
}
