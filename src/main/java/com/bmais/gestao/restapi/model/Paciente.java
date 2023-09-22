package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.PacienteMapper;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.restmodel.RestPaciente;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PACIENTE")
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Paciente extends Model<RestPaciente> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PACIENTE")
	@SequenceGenerator(name = "SEQ_PACIENTE", sequenceName = "SEQ_PACIENTE", allocationSize = 1)
	@Include
	@Column(name = "PAC_ID")
	private Long id;
	
	@Column(name = "PAC_NOME")
	private String nome;
	
	@Column(name = "PAC_DT_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "PAC_MATRICULA")
	private String matricula;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PAC_SEXO")
	private Sexo sexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private Cliente cliente;

    @OneToMany(mappedBy="paciente", fetch = FetchType.LAZY)
    private List<Internacao> internacoes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPA_ID")
	private StatusPaciente status;

	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private StatusPaciente statusAnterior;

	@Column(name = "PAC_OBSERVACAO")
	private String observacao;

	public Paciente(Long id){
		this.setId(id);
	}

	public Paciente(Long id, String nome){
		this.setId(id);
		this.setNome(nome);
	}

	public Paciente(Long id, String nome, LocalDate dataNascimento, Sexo sexo, String matricula, Long idCliente, String razaoCliente){
		this.setId(id);
		this.setNome(nome);
		this.setDataNascimento(dataNascimento);
		this.setSexo(sexo);
		this.setMatricula(matricula);
		Cliente cliente = new Cliente(idCliente, razaoCliente);
		this.setCliente(cliente);
	}

	public Paciente(Sexo sexo, Long idCliente){
		this.setSexo(sexo);
		Cliente cliente = new Cliente(idCliente);
		this.setCliente(cliente);
	}

	public Paciente (Long id, String nome, LocalDate dataNascimento, String matricula, Cliente cliente){
		this.setId(id);
		this.setNome(nome);
		this.setDataNascimento(dataNascimento);
		this.setMatricula(matricula);
		this.setCliente(cliente);
	}

	public Paciente(Long id, String nomePaciente, LocalDate dataNascimento, String matricula, Long idCliente, String nomeCliente, StatusPaciente status, Sexo sexo){
		this.setId(id);
		Cliente cliente = new Cliente();
		this.setNome(nomePaciente);
		this.setDataNascimento(dataNascimento);
		this.setMatricula(matricula);
		cliente.setId(idCliente);
		cliente.setRazaoSocial(nomeCliente);
		this.setStatus(status);
		this.setCliente(cliente);
		this.setSexo(sexo);
	}

	public Paciente(Long idPaciente, String nomePaciente, LocalDate dataNascimento, Sexo sexo, String matricula,
					Long idCliente, String razaCliente, String observacao, Long idStatus, String status){
		this.setId(idPaciente);
		this.setNome(nomePaciente);
		this.setDataNascimento(dataNascimento);
		this.setSexo(sexo);
		this.setMatricula(matricula);
		this.setObservacao(observacao);
		Cliente cliente = new Cliente();
		cliente.setId(idCliente);
		cliente.setRazaoSocial(razaCliente);
		this.setCliente(cliente);
		this.setStatus(new StatusPaciente(idStatus, status));
	}

	@PrePersist
	private void preInsert()
	{
		this.status = new StatusPaciente(StatusPaciente.ATIVO);
	}

	@PreUpdate
	private void preUpdate()
	{
		this.status = statusAnterior;
	}

	@PostLoad
	private void postLoad()
	{
		this.statusAnterior = status;
	}

	@Override
	public RestPaciente modelParaRest() {
		return PacienteMapper.INSTANCE.convertToRest(this);
	}
	
	public RestPaciente modelParaSimpleRest()
	{
		return PacienteMapper.INSTANCE.convertToSimpleRest(this);
	}
}
