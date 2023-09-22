package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCapeante
{
	AM("Ambulatório"),
	PS("Pronto Socorro");
	
	private String descricao;
}
