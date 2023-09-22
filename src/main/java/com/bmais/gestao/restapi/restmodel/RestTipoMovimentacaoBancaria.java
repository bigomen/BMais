package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import com.bmais.gestao.restapi.model.TipoMovimentacaoBancaria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoMovimentacaoBancaria extends RestModel<TipoMovimentacaoBancaria> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @JsonProperty(value = "descricao")
    private String descricao;

	@Override
	public TipoMovimentacaoBancaria restParaModel()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
