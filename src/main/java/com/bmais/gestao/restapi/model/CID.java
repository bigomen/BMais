package com.bmais.gestao.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.CIDMapper;
import com.bmais.gestao.restapi.restmodel.RestCID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CID")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CID extends Model<RestCID>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CID")
	@SequenceGenerator(name = "SEQ_CID", sequenceName = "SEQ_CID", allocationSize = 1)
	@Column(name = "CID_ID")
	private Long id;
	
	@Column(name = "CID_CODIGO", unique = true)
	@EqualsAndHashCode.Include
	private String codigo;
	
	@Column(name = "CID_DESCRICAO")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "SCI_ID")
	private StatusCID status;

	public CID(Long id)
	{
		super();
		this.id = id;
	}
	
	public CID(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public CID(Long id, String codigo, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
	}

	@PrePersist
	private void prePersist() {
		this.status = new StatusCID(StatusCID.ATIVO);
	}

	@Override
	public RestCID modelParaRest() {
		return CIDMapper.INSTANCE.convertToRest(this);
	}

}
