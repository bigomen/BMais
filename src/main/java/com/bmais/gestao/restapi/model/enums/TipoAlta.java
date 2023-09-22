package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAlta
{
	A("Alta"),
	T("Transferência"),
	H("Home Care");

	private String descricao;
}
