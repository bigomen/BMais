package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusGlosaMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusGlosa;
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
@Table(name = "STATUS_TIPO_GLOSA")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusGlosa extends Model<RestStatusGlosa> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@Column(name = "STG_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "STG_DESCRICAO")
	private String descricao;

	public StatusGlosa(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusGlosa modelParaRest() {
		return StatusGlosaMapper.INSTANCE.convertToRest(this);
	}
}
