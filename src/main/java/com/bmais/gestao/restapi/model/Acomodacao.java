package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AcomodacaoMapper;
import com.bmais.gestao.restapi.restmodel.RestAcomodacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.Immutable;
import java.io.Serializable;

@Entity
@Table(name = "ACOMODACAO")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class Acomodacao extends Model<RestAcomodacao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACOMODACAO")
	@SequenceGenerator(name = "SEQ_ACOMODACAO", sequenceName = "SEQ_ACOMODACAO", allocationSize = 1)
	@Column(name = "ACO_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "ACO_DESCRICAO")
	private String descricao;

	@Override
	public RestAcomodacao modelParaRest(){
		return AcomodacaoMapper.INSTANCE.convertToRest(this);
	}

	public Acomodacao(Long id)
	{
		super();
		this.id = id;
	}
}
