package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EscolaridadeMapper;
import com.bmais.gestao.restapi.restmodel.RestEscolaridade;
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
@Table(name = "ESCOLARIDADE")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@Immutable
public class Escolaridade extends Model<RestEscolaridade> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "ESO_ID")
	private Long id;
	
	@Column(name = "ESO_DESCRICAO")
	private String descricao;

	@Override
	public RestEscolaridade modelParaRest() {
		return EscolaridadeMapper.INSTANCE.convertToRest(this);
	}
}
