package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.bmais.gestao.restapi.model.enums.TipoAlta;
import lombok.Data;

@Data
@Embeddable
public class Alta implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private TipoAlta tipo;
}
