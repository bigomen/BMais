package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EstadoCivilMapper;
import com.bmais.gestao.restapi.restmodel.RestEstadoCivil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ESTADO_CIVIL")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Immutable
public class EstadoCivil extends Model<RestEstadoCivil> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTADO_CIVIL")
	@SequenceGenerator(name = "SEQ_ESTADO_CIVIL", sequenceName = "SEQ_ESTADO_CIVIL", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "ESC_ID")
	private Long id;
	
	@Column(name = "ESC_DESCRICAO")
	private String descricao;

	@Override
	public RestEstadoCivil modelParaRest() {
		return EstadoCivilMapper.INSTANCE.convertToRest(this);
	}
}
