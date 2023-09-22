package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContaBancaria
{
	C("Conta-Salário"),
	P("Poupança");
	
	private String descricao;
}
