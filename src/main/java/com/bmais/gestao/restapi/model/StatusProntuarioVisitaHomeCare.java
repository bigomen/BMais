package com.bmais.gestao.restapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.bmais.gestao.restapi.mapper.StatusProntuarioVisitaHomeCareMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusProntuarioVisitaHomeCare;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STATUS_PRONTUARIO_VISITA_HOMECARE")
@Getter
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StatusProntuarioVisitaHomeCare extends Model<RestStatusProntuarioVisitaHomeCare> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long ATIVO = 1L;
	public static final long INATIVO = 2L;
	public static final long EXCLUIDO = 3L;

	@Id
	@Column(name = "SPH_ID", nullable = false)
	@Setter
	private Long id;

	@Column(name = "SPH_DESCRICAO")
	private String descricao;

	public StatusProntuarioVisitaHomeCare(Long id) {
		this.id = id;
	}

	@Override
	public RestStatusProntuarioVisitaHomeCare modelParaRest() {
		return StatusProntuarioVisitaHomeCareMapper.INSTANCE.convertToRest(this);
	}
}
