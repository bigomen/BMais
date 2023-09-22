package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.HospitalClienteMapper;
import com.bmais.gestao.restapi.model.HospitalCliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestHospitalCliente extends RestModel<HospitalCliente> implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "hospital")
    private RestHospital hospital;

    @JsonProperty(value = "codigoCliente")
    private String codigoCliente;

    @JsonProperty(value = "dg")
    private boolean dg;

    @JsonProperty("servico")
    private RestServico servico;

	@Override
	public HospitalCliente restParaModel()
	{
		return HospitalClienteMapper.INSTANCE.toModelHospitalCliente(this);
	}
}
