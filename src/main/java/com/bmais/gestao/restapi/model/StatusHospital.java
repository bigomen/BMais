package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusHospitalMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusHospital;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_HOSPITAL")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
public class StatusHospital extends Model<RestStatusHospital> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "STH_ID", nullable = false)
	private Long id;
	
	@Column(name = "STH_DESCRICAO")
	private String descricao;

	@Override
	public RestStatusHospital modelParaRest() {
		return StatusHospitalMapper.INSTANCE.convertToRest(this);
	}
}
