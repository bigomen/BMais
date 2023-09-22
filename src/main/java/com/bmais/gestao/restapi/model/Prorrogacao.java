package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Prorrogacao implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OffsetDateTime dtAte;
	public String obs;
}