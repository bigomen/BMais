package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ObservacaoAvaliacaoMapper;
import com.bmais.gestao.restapi.restmodel.RestObservacaoAvaliacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "OBSERVACAO_AVALIACAO")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ObservacaoAvaliacao extends Model<RestObservacaoAvaliacao> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "OBA_ID")
	private Long id;
	
	@Column(name = "OBA_DESCRICAO")
	private String descricao;
	
	@Override
	public RestObservacaoAvaliacao modelParaRest(){
		return ObservacaoAvaliacaoMapper.INSTANCE.convertToRest(this);
	}
}
