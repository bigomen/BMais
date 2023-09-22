package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoAgendaPesquisa
{
	A("Administrativo"),
	C("Cliente"),
	E("Enfermeiro"),
	H("Hospital"),
	M("Médico");
	
	private String descricao;
}
