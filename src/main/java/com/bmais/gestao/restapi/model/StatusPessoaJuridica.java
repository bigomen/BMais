package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusPessoaJuridicaMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusPessoaJuridica;
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
@Table(name = "STATUS_PESSOA_JURIDICA")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusPessoaJuridica extends Model<RestStatusPessoaJuridica> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "SPJ_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SPJ_DESCRICAO")
	private String descricao;

	public StatusPessoaJuridica(Long id) {
		this.id = id;
	}

	public StatusPessoaJuridica(String descricao)
	{
		this.descricao = descricao;
	}

	public StatusPessoaJuridica(Long id, String descricao)
	{
		this.id = id;
		this.descricao = descricao;
	}

	@Override
	public RestStatusPessoaJuridica modelParaRest() {
		return StatusPessoaJuridicaMapper.INSTANCE.convertToRest(this);
	}
}
