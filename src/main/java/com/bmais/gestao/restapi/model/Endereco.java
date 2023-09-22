package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EnderecoMapper;
import com.bmais.gestao.restapi.restmodel.RestEndereco;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ENDERECO")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Endereco extends Model<RestEndereco>implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
	@SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
	@Column(name = "END_ID")
	private Long id;

	@Column(name = "END_LOGRADOURO")
	private String logradouro;

	@Column(name = "END_COMPLEMENTO")
	private String complemento;

	@Column(name = "END_BAIRRO")
	private String bairro;

	@Column(name = "END_NUMERO")
	private String numero;

	@Column(name = "END_CEP")
	private String cep;

	@ManyToOne
	@JoinColumn(name = "MUN_ID")
	private Municipio municipio;

	public Endereco(Long id, Municipio municipio){
		this.id = id;
		this.municipio = municipio;
	}

	public RestEndereco modelParaRest() {
		return EnderecoMapper.convertToRest(this);
	}
}
