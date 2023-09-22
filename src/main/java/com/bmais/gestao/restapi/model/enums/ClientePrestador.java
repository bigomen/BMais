package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientePrestador
{
	C("Cliente"),
	P("FornecedorAuditoria");
	
	private String descricao;
}
