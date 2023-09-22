package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoHistorico
{
	I("Inclusão"),
	A("Alteração"),
	F("Finalização"),
	R("Remoção");
	
	private String descricao;
}
