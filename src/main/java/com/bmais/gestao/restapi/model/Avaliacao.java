package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AvaliacaoMapper;
import com.bmais.gestao.restapi.restmodel.RestAvaliacao;
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
@Table(name = "AVALIACAO")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Avaliacao extends Model<RestAvaliacao> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "AVA_ID")
	private Long id;
	
	@Column(name = "AVA_DESCRICAO")
	private String descricao;

	@Override
	public RestAvaliacao modelParaRest(){
		return AvaliacaoMapper.INSTANCE.convertToRest(this);
	}
}
