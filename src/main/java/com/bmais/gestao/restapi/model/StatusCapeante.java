package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusCapeanteMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusCapeante;
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
@Table(name = "STATUS_CAPEANTE")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusCapeante extends Model<RestStatusCapeante> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final Long ATIVO = 1L;
	public static final Long INATIVO = 2L;
	public static final Long EXCLUIDO = 3L;
	public static final Long RASCUNHO = 4L;
	public static final Long PENDENTE = 5L;
	public static final Long APROVADO = 6L;
	public static final Long RECUSADO = 7L;
	public static final Long FINALIZADO = 8L;

	@Id
	@Column(name = "SCA_ID")
	@Setter
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "SCA_DESCRICAO")
	private String descricao;

	public StatusCapeante(Long id) {
		this.id = id;
	}

	public StatusCapeante(String descricao){
		this.descricao = descricao;
	}

	@Override
	public RestStatusCapeante modelParaRest() {
		return StatusCapeanteMapper.INSTANCE.convertToRest(this);
	}
}
