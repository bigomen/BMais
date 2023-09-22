package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EmpresaMapper;
import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "EMPRESA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class Empresa extends Model<RestEmpresa> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPRESA")
	@SequenceGenerator(name = "SEQ_EMPRESA", sequenceName = "SEQ_EMPRESA", allocationSize = 1)
	@Column(name = "EMP_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "EMP_RAZAO_SOCIAL")
	private String razaoSocial;

	@Column(name = "EMP_CNPJ")
	private String cnpj;

	@Column(name = "EMP_IE")
	private String ie;

	@Column(name = "EMP_IM")
	private String im;

	@Column(name = "EMP_EMAIL")
	private String email;

	@Column(name = "EMP_TELEFONE")
	private String telefone;

	@Column(name = "EMP_DT_INICIO")
	private LocalDate data_inicio;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@Column(name = "EMP_SEDE")
	private Boolean sede;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID_SEDE", referencedColumnName = "EMP_ID")
	private Empresa empresa;

	@Column(name = "EMP_DT_FIM")
	private LocalDate data_fim;

	@ManyToOne
	@JoinColumn(name = "SEM_ID")
	private StatusEmpresa status;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Collection<Usuario> usuarios;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	protected Collection<DadosBancarios> dadosBancarios;

	@Column(name = "EMP_OBSERVACAO")
	private String observacao;

	public Empresa(Long id){
		this.setId(id);
	}

	public Empresa(Long id, String razao){
		this.setId(id);
		this.setRazaoSocial(razao);
	}

	public Empresa(Long id, String razaoSocial, String cnpj, Long municipioId, String municipio, UF uf, Boolean sede, Long empresaSede){
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		Endereco end = new Endereco();
		Municipio cidade = new Municipio();
		cidade.setId(municipioId);
		cidade.setNome(municipio);
		cidade.setUf(uf);
		end.setMunicipio(cidade);
		this.endereco = end;
		this.sede = sede;
		Empresa emp = new Empresa();
		emp.setId(empresaSede);
		this.empresa = emp;
	}

	@Override
	public RestEmpresa modelParaRest() {
		return EmpresaMapper.INSTANCE.convertToRest(this);
	}
}
