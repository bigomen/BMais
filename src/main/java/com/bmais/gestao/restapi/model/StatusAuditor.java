package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusAuditorMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusAuditor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_AUDITOR")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class StatusAuditor extends Model<RestStatusAuditor> implements Serializable
{

	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "SAU_ID")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "SAU_DESCRICAO")
	private String descricao;

	public StatusAuditor(Long id)
	{
		this.id = id;
	}

	public StatusAuditor(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public RestStatusAuditor modelParaRest(){return StatusAuditorMapper.INSTANCE.convertToRest(this);}
}
