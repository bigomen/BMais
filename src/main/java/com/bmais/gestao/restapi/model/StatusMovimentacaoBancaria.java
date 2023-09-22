package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.bmais.gestao.restapi.restmodel.RestStatusMovimentacaoBancaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "STATUS_MOVIMENTACAO_BANCARIA")
@Data
@Immutable
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class StatusMovimentacaoBancaria extends Model<RestStatusMovimentacaoBancaria> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SMB_ID")
	@NonNull
	@Include
	private Long id;
	
	@Column(name = "SMB_DESCRICAO")
	private String descricao;

	@Override
	public RestStatusMovimentacaoBancaria modelParaRest()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
