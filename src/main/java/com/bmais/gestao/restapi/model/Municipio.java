package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.MunicipioMapper;
import com.bmais.gestao.restapi.restmodel.RestMunicipio;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MUNICIPIO")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Municipio extends Model<RestMunicipio> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "MUN_ID")
	private Long id;
	
	@Column(name = "MUN_DESCRICAO")
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UF_ID")
	private UF uf;

	public Municipio(Long id){
		this.setId(id);
	}

	@Override
	public RestMunicipio modelParaRest() {
		return MunicipioMapper.INSTANCE.convertToRest(this);
	}
}
