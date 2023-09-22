package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusUsuarioMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "STATUS_USUARIO")
@Getter
@Immutable
@NoArgsConstructor
public class StatusUsuario extends Model<RestStatusUsuario> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "SUS_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SUS_DESCRICAO")
	private String descricao;

	public StatusUsuario(Long id) {
		this.id = id;
	}

	public StatusUsuario(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	@Override
	public RestStatusUsuario modelParaRest() {
		return StatusUsuarioMapper.INSTANCE.convertToRest(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		StatusUsuario that = (StatusUsuario) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
