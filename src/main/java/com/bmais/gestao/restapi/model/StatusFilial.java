package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusFilialMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusFilial;
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
@Table(name = "STATUS_FILIAL")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class StatusFilial extends Model<RestStatusFilial> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;

	@Id
	@Column(name = "SFI_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SFI_DESCRICAO")
	private String descricao;

	public StatusFilial(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusFilial modelParaRest() {
		return StatusFilialMapper.INSTANCE.convertToRest(this);
	}
}
