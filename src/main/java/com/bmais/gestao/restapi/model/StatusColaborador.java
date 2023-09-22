package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusColaboradorMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusColaborador;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_COLABORADOR")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusColaborador extends Model<RestStatusColaborador> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "STC_ID")
	private Long id;
	
	@Column(name = "STC_DESCRICAO")
	private String descricao;

	public StatusColaborador(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusColaborador modelParaRest() {
		return StatusColaboradorMapper.INSTANCE.convertToRest(this);
	}
}
