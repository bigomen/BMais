package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.JustificativaPermanenciaMapper;
import com.bmais.gestao.restapi.restmodel.RestJustificativaPermanencia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "JUSTIFICATIVA_PERMANENCIA")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class JustificativaPermanencia extends Model<RestJustificativaPermanencia> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "JUP_ID")
	private Long id;

	@Column(name = "JUP_DESCRICAO")
	private String descricao;

	@Override
	public RestJustificativaPermanencia modelParaRest(){
		return JustificativaPermanenciaMapper.INSTANCE.convertToRest(this);
	}
}
