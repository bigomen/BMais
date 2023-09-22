package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.bmais.gestao.restapi.restmodel.RestEvolucaoVisita;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EVOLUCAO_VISITA")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class EvolucaoVisita extends Model<RestEvolucaoVisita> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVOLUCAO_INTERNACAO")
	@SequenceGenerator(name = "SEQ_EVOLUCAO_INTERNACAO", sequenceName = "SEQ_EVOLUCAO_INTERNACAO", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "EVI_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIS_ID", updatable = false)
	private Visita visita;
	
	@Column(name = "EVI_DH_EVOLUCAO", updatable = false)
	@Setter(value = AccessLevel.NONE)
	private LocalDate dataEvolucao;
	
	@Column(name = "EVI_EVOLUCAO")
	private String descricao;

	@Override
	public RestEvolucaoVisita modelParaRest()
	{
		RestEvolucaoVisita rest = new RestEvolucaoVisita();
		rest.setDescricao(this.descricao);
		rest.setDataEvolucao(this.dataEvolucao);
		rest.setId(UtilSecurity.encryptId(id));
		return rest;
	}
	
	@PrePersist
	private void prePersist()
	{
		this.dataEvolucao = this.getVisita().getData();
	}

}
