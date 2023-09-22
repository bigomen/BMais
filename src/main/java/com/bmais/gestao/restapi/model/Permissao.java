package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "PERMISSAO")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissao implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERMISSAO")
	@SequenceGenerator(name = "SEQ_PERMISSAO", sequenceName = "SEQ_PERMISSAO", allocationSize = 1)
	@Column(name = "PER_ID")
	private Integer id;
	
	@Include
	@Column(name = "PER_DESCRICAO")
	private String descricao;
}
