package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import com.bmais.gestao.restapi.model.PlanoContas;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestPlanoContas extends RestModel<PlanoContas> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PlanoContas restParaModel()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
