package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusTussMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusTuss;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_TUSS")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusTuss extends Model<RestStatusTuss> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@Column(name = "STU_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "STU_DESCRICAO")
	private String descricao;

	public StatusTuss(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusTuss modelParaRest() {
		return StatusTussMapper.INSTANCE.convertToRest(this);
	}
}
