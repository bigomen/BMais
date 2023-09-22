package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.UfMapper;
import com.bmais.gestao.restapi.restmodel.RestUf;
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
@Table(name = "UF")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UF extends Model<RestUf> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "UF_ID")
	private Long id;

	@Column(name = "UF_SIGLA")
	private String sigla;
	
	@Column(name = "UF_DESCRICAO")
	private String descricao;

	@Override
	public RestUf modelParaRest() {
		return UfMapper.INSTANCE.convertToRest(this);
	}
}
