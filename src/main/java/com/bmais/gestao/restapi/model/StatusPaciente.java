package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusPacienteMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusPaciente;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_PACIENTE")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StatusPaciente extends Model<RestStatusPaciente> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "SPA_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SPA_DESCRICAO")
	private String descricao;

	public StatusPaciente(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusPaciente modelParaRest() {
		return StatusPacienteMapper.INSTANCE.convertToRest(this);
	}
}
