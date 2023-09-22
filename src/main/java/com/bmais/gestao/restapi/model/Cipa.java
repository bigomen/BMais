package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.CipaMapper;
import com.bmais.gestao.restapi.restmodel.RestCipa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CIPA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Cipa extends Model<RestCipa> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIPA")
	@SequenceGenerator(name = "SEQ_CIPA", sequenceName = "SEQ_CIPA", allocationSize = 1)
	@Column(name = "CIP_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID")
	private Colaborador colaborador;

	@Column(name = "CIP_INICIO_GESTAO")
	private LocalDate inicioGestao;

	@Column(name = "CIP_FIM_GESTAO")
	private LocalDate fimGestao;

	@Column(name = "CIP_INICIO_ESTABILIDADE")
	private LocalDate inicioEstabilidade;

	@Column(name = "CIP_FIM_ESTABILIDADE")
	private LocalDate fimEstabilidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID")
	private Usuario usuario;

	@Column(name = "CIP_DT_CADASTRO")
	private LocalDate cadastro;

	@Column(name = "CIP_TIPO")
	private String tipo;

	public Cipa(Long id, LocalDate inicioGestao, LocalDate fimGestao, LocalDate inicioEstabilidade, LocalDate fimEstabilidade, LocalDate cadastro, String tipo, Long idUsuario, String email) {
		this.id = id;
		this.inicioGestao = inicioGestao;
		this.fimGestao = fimGestao;
		this.inicioEstabilidade = inicioEstabilidade;
		this.fimEstabilidade = fimEstabilidade;
		this.cadastro = cadastro;
		this.tipo = tipo;
		this.usuario = new Usuario(idUsuario, email);
	}

	@Override
	public RestCipa modelParaRest() {
		return CipaMapper.INSTANCE.convertToRest(this);
	}
}
