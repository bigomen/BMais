package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.RamoAtividadeMapper;
import com.bmais.gestao.restapi.restmodel.RestRamoAtividade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "RAMO_ATIVIDADE")
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class RamoAtividade  extends Model<RestRamoAtividade>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RAMO_ATIVIDADE")
	@SequenceGenerator(name = "SEQ_RAMO_ATIVIDADE", sequenceName = "SEQ_RAMO_ATIVIDADE", allocationSize = 1)
	@Column(name = "RAT_ID")
	private Long id;
	
	@Column(name = "RAT_DESCRICAO")
	private String descricao;

	@Override
	public RestRamoAtividade modelParaRest() {
		return RamoAtividadeMapper.INSTANCE.convertToRest(this);
	}
}
