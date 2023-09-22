package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAcomodacao
{
	A("APARTAMENTO"),
	B("BERÇÁRIO"),
	E("ENFERMARIA"),
	S("SEMI-INTENSIVO"),
	U("UTI");
	
	private String descricao;
}
