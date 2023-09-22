package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EvolucaoColaboradorMapper;
import com.bmais.gestao.restapi.restmodel.RestEvolucaoColaborador;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EVOLUCAO_COLABORADOR")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class EvolucaoColaborador extends Model<RestEvolucaoColaborador> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVOLUCAO_COLABORADOR")
	@SequenceGenerator(name = "SEQ_EVOLUCAO_COLABORADOR", sequenceName = "SEQ_EVOLUCAO_COLABORADOR", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "ECO_ID")
	private Long id;

	@Column(name = "ECO_DT_INICIO")
	private LocalDate dataInicio;

	@Column(name = "ECO_AREA_SETOR")
	private String areaSetor;

	@Column(name = "ECO_DESCRICAO")
	private String descricao;

	@Column(name = "ECO_SALARIO")
	private BigDecimal salario;

	@ManyToOne
	@JoinColumn(name = "CAR_ID")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID")
	private Colaborador colaborador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_ID")
	private Usuario usuario;

	@Column(name = "ECO_DT_ALTERACAO")
	private LocalDate dataAlteracao;

	public EvolucaoColaborador(Long id, LocalDate dataInicio, String areaSetor, String descricao, BigDecimal salario, Long idCargo, String cargoDescricao) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.areaSetor = areaSetor;
		this.descricao = descricao;
		this.salario = salario;
		this.setCargo(new Cargo(idCargo, cargoDescricao));
	}

	@Override
	public RestEvolucaoColaborador modelParaRest(){
		return EvolucaoColaboradorMapper.INSTANCE.convertToRest(this);
	}
}
