package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.BancoMapper;
import com.bmais.gestao.restapi.restmodel.RestBanco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BANCO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Banco extends Model<RestBanco> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BAN_ID")
	private Long id;
	
	@Column(name = "BAN_CODIGO", unique = true)
	@Include
	private String codigo;
	
	@Column(name = "BAN_DESCRICAO", nullable = false)
	private String nome;

	public Banco(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public RestBanco modelParaRest() {
		return BancoMapper.INSTANCE.convertToRest(this);
	}
}
