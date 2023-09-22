package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.GrupoUsuarioMapper;
import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.bmais.gestao.restapi.restmodel.RestGrupoUsuario;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "GRUPO_USUARIO")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GrupoUsuario extends Model<RestGrupoUsuario> implements Serializable
{

	private static final long serialVersionUID = 1L;

	public static final long GERENTE = 8L;
	public static final long AUDITOR_MEDICO = 19L;
	public static final long AUDITOR_ENFERMEIRO = 20L;
	public static final long CLIENTE = 21L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
	@SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "SEQ_GRUPO_USUARIO", allocationSize = 1)
	@Column(name = "GRU_ID")
	private Long id;
	
	@Include
	@Column(name = "GRU_DESCRICAO")
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GRUPO_USUARIO_PERMISSAO",
			joinColumns = @JoinColumn(name = "GRU_ID"),
			inverseJoinColumns = @JoinColumn(name = "PER_ID"))
	@ToString.Exclude
	private Collection<Permissao> permissoes;

	@Enumerated(EnumType.STRING)
	@Column(name = "GRU_CATEGORIA")
	private CategoriaGrupoUsuario categoria;

	public GrupoUsuario(Long id, String descricao){
		this.setId(id);
		this.setDescricao(descricao);
	}

	public GrupoUsuario(Long id) {
		this.id = id;
	}

	@Override
	public RestGrupoUsuario modelParaRest() {
		return GrupoUsuarioMapper.INSTANCE.convertToRest(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		GrupoUsuario that = (GrupoUsuario) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
