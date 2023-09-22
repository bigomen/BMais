package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.CTPSMapper;
import com.bmais.gestao.restapi.restmodel.RestCTPS;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class CTPS implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numero;
	private String serie;
	
	@ManyToOne
	private UF uf;

	public RestCTPS modelParaRest() {
		return CTPSMapper.INSTANCE.convertToRest(this);
	}
}
