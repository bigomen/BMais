package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.bmais.gestao.restapi.mapper.PlanoContasMapper;
import com.bmais.gestao.restapi.restmodel.RestPlanoContas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "PLANO_CONTAS")
@Data
@Immutable
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class PlanoContas extends Model<RestPlanoContas> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PCO_ID")
	@Include
	@NonNull
	private Long id;
	
	@Column(name = "PCO_DESCRICAO")
	private String descricao;
	
	@Column(name = "PCO_TIPO")
	private String tipo;
	
	@Override
	public RestPlanoContas modelParaRest()
	{
		return PlanoContasMapper.INSTANCE.convertToRest(this);
	}
}
