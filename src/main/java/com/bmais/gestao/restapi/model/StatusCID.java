package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusCIDMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusCID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_CID")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusCID extends Model<RestStatusCID> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@Column(name = "SCI_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SCI_DESCRICAO")
	private String descricao;

	public StatusCID(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusCID modelParaRest() {
		return StatusCIDMapper.INSTANCE.convertToRest(this);
	}
}
