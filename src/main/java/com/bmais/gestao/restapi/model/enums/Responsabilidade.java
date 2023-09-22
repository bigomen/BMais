package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Responsabilidade
{
	E("Enfermeiro"),
	M("Médico");
	
	private String descricao;
}
