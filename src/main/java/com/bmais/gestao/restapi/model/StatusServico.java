package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusServicoMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusServico;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_SERVICO")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusServico extends Model<RestStatusServico> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@Column(name = "SSE_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SSE_DESCRICAO")
	private String descricao;

	public StatusServico(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusServico modelParaRest() {
		return StatusServicoMapper.INSTANCE.convertToRest(this);
	}
}
