package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TIPO_AUDITORIA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoAuditoria implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_AUDITORIA")
	@SequenceGenerator(name = "SEQ_TIPO_AUDITORIA", sequenceName = "SEQ_TIPO_AUDITORIA", allocationSize = 1)
	@Include
	@Column(name = "TIA_ID")
	private Integer id;
	
	@Column(name = "TIA_DESCRICAO")
	private String descricao;
	
	@Column(name = "TIA_SIGLA")
	private String sigla;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tipoAuditoria")
	private Collection<AuditorHospitalAuditoria> hospitais; 
}
