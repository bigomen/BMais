package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditorHospitalAuditoriaID implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "HOS_ID")
	private Integer idHospital;
	
	@Column(name = "AUD_ID")
	private Integer idAuditor;
	
	@Column(name = "TIA_ID")
	private Integer idTipoAuditoria;
}
