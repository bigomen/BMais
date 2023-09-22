package com.bmais.gestao.restapi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUDITOR_HOSPITAL")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
public class AuditorHospitalAuditoria
{
	@EmbeddedId
	@Include
	@NonNull
	private AuditorHospitalAuditoriaID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOS_ID", insertable = false, updatable = false)
	private Hospital hospital;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID", insertable = false, updatable = false)
	private Auditor auditor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIA_ID", insertable = false, updatable = false)
	private TipoAuditoria tipoAuditoria;
}
