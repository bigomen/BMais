package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EmergenciaMapper;
import com.bmais.gestao.restapi.restmodel.RestEmergencia;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Emergencia implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;
	private String parentesco;
	private String endereco;
	private String telefone;
	private String celular;
	private String telefoneComercial;

	public RestEmergencia modelParaRest() {
		return EmergenciaMapper.INSTANCE.convertToRest(this);
	}
}
