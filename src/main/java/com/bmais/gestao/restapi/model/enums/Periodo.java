package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Periodo
{
	M("Manhã"),
	T("Tarde"),
	D("Diário");
	
	private String descricao;
}
