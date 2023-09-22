package com.bmais.gestao.restapi.model;

import javax.persistence.*;

import com.bmais.gestao.restapi.mapper.DiariaMapper;
import com.bmais.gestao.restapi.restmodel.RestDiaria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "DIARIA")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class Diaria extends Model<RestDiaria> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIARIA")
	@SequenceGenerator(name = "SEQ_DIARIA", sequenceName = "SEQ_DIARIA", allocationSize = 1)
	@Column(name = "DIA_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "DIA_DIARIAS_COBRADAS")
	private Integer cobradas;

	@Column(name = "DIA_DIARIAS_GLOSADAS")
	private Integer glosadas;

	@Column(name = "DIA_VALOR_APRESENTADO")
	private BigDecimal valorApresentado;

	@Column(name = "DIA_VALOR_GLOSA")
	private BigDecimal valorGlosa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACO_ID")
	private Acomodacao acomodacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAP_ID")
	private CapeanteInternacao capeante;

	@Override
	public RestDiaria modelParaRest(){
		return DiariaMapper.INSTANCE.convertToRest(this);
	}
}
