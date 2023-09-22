package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTratamento 
{
	A("Acidente Pessoal"),
	E("Eletivo"),
	U("Urgência");
	
	private String descricao;
}
