package com.bmais.gestao.restapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoInternacao
{
	CI("Cirúrgica"),
	CL("Clínica");
	
	private String descricao;
}
