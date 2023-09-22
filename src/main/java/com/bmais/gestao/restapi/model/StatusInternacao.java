package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusInternacaoMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusInternacao;
import lombok.AllArgsConstructor;
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
@Table(name = "STATUS_INTERNACAO")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StatusInternacao extends Model<RestStatusInternacao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "SIN_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SIN_DESCRICAO")
	private String descricao;

	public StatusInternacao(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusInternacao modelParaRest() {
		return StatusInternacaoMapper.INSTANCE.convertToRest(this);
	}
}
