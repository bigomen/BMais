package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo
{
	F("Feminino"),
	M("Masculino");
	
	private final String descricao;
}
