package com.bmais.gestao.restapi.model;

import javax.persistence.*;
import com.bmais.gestao.restapi.mapper.ContatoMapper;
import com.bmais.gestao.restapi.restmodel.RestContato;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "CONTATO")
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Contato extends Model<RestContato>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTATO")
	@SequenceGenerator(name = "SEQ_CONTATO", sequenceName = "SEQ_CONTATO", allocationSize = 1)
	@Column(name = "CON_ID")
	private Long id;

	@Column(name = "CON_NOME")
	private String nome;
	
	@Column(name = "CON_TELEFONE")
	private String telefone;
	
	@Column(name = "CON_CELULAR")
	private String celular;
	
	@Column(name = "CON_CPF")
	private String cpf;
	
	@Column(name = "CON_CARGO")
	private String cargo;
	
	@Column(name = "CON_EMAIL")
	private String email;
	
	@Column(name = "CON_PARENTESCO")
	private String parentesco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@Column(name = "CON_FINANCEIRO")
	private Boolean financeiro;

	@Column(name = "CON_RAMAL")
	private String ramal;

	@Column(name = "CON_OBSERVACAO")
	private String observacao;
	
	@Column(name = "CON_RECEBE_EMAIL")
	private Boolean recebeEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private PessoaJuridica pessoaJuridica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOS_ID")
	private Hospital hospital;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID")
	private Colaborador colaborador;

	public Contato(Long id, String nome, String telefone, String celular, String cargo, String email, Boolean financeiro) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
		this.cargo = cargo;
		this.email = email;
		this.financeiro = financeiro;
	}

	@Override
	public RestContato modelParaRest(){return ContatoMapper.INSTANCE.convertToRest(this);}
}
