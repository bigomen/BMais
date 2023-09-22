package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.CargoMapper;
import com.bmais.gestao.restapi.restmodel.RestCargo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CARGO")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Cargo extends Model<RestCargo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARGO")
	@SequenceGenerator(name = "SEQ_CARGO", sequenceName = "SEQ_CARGO", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "CAR_ID")
	private Long id;
	
	@Column(name = "CAR_DESCRICAO")
	private String descricao;

	public Cargo(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	@Override
	public RestCargo modelParaRest(){
		return CargoMapper.INSTANCE.convertToRest(this);
	}
}
