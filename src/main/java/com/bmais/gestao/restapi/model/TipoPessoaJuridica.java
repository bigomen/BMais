package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoPessoaJuridicaMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoPessoaJuridica;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_PESSOA_JURIDICA")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoPessoaJuridica extends Model<RestTipoPessoaJuridica> implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "TPJ_ID")
	private Long id;
	
	@Column(name = "TPJ_DESCRICAO")
	private String descricao;

	@Override
	public RestTipoPessoaJuridica modelParaRest() {
		return TipoPessoaJuridicaMapper.INSTANCE.convertToRest(this);
	}
}
