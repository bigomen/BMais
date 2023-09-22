package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.UsuarioMapper;
import com.bmais.gestao.restapi.restmodel.RestUsuario;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Usuario extends Model<RestUsuario> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
	@Column(name = "USU_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "USU_NOME")
	private String nome;

	@Column(name = "USU_EMAIL")
	private String email;

	@Column(name = "USU_SENHA")
	private String senha;

	@ManyToOne
	@JoinColumn(name = "SUS_ID")
	private StatusUsuario status;

	@Column(name = "USU_RESET_PASSWORD_TOKEN")
	private String resetPasswordToken;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private Cliente cliente;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID")
	@ToString.Exclude
	private Auditor auditor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRU_ID")
	private GrupoUsuario grupo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID")
	@ToString.Exclude
	private Colaborador colaborador;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USUARIO_EMPRESA",
			joinColumns = @JoinColumn(name = "USU_ID"),
			inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
	private Collection<Empresa> empresas;

	@Column(name = "USU_DATA_ACESSO")
	private LocalDate dataInicio;

	@Column(name = "USU_DATA_FIM")
	private LocalDate dataFim;

//	@Transient
//	private Object[] dadosAnteriores;

	public Usuario(Long id){
		this.setId(id);
	}

	public Usuario(Long id, String email){
		this.setId(id);
		this.setEmail(email);
	}

	public Usuario(Long id, String nome, String email){
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
	}

	public Usuario(Long id, Long idGrupo, String nome, String grupoDescricao, String email){
		this.setId(id);
		this.setNome(nome);
		this.setGrupo(new GrupoUsuario(idGrupo, grupoDescricao));
		this.setEmail(email);
	}

	public Usuario(Long id, String nome, String email, StatusUsuario status) {
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setStatus(status);
	}

	public Usuario(Long id, String nome, String email, LocalDate dataInicio, LocalDate dataFim, Long idGrupo, String descricaoGrupo, Long idStatus, String descricaoStatus) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.grupo = new GrupoUsuario(idGrupo, descricaoGrupo);
		this.status = new StatusUsuario(idStatus, descricaoStatus);
	}

	@Override
	public RestUsuario modelParaRest() {
		return UsuarioMapper.INSTANCE.convertToRest(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Usuario usuario = (Usuario) o;
		return getId() != null && Objects.equals(getId(), usuario.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
