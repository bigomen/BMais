package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.HospitalMapper;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "HOSPITAL")
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Hospital extends Model<RestHospital> implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HOSPITAL")
	@SequenceGenerator(name = "SEQ_HOSPITAL", sequenceName = "SEQ_HOSPITAL", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "HOS_ID")
	private Long id;
	
	@Column(name = "HOS_RAZAO_SOCIAL", unique = true)
	private String razaoSocial;
	
	@Column(name = "HOS_CNPJ", unique = true)
	private String cnpj;
	
	@Column(name = "HOS_TELEFONE")
	private String telefone;
	
	@Column(name = "HOS_EMAIL")
	private String email;

	@Column(name = "HOS_AUDITAVEL")
	private Boolean auditavel;

	@Column(name = "HOS_ADMINISTRATIVO")
	private Boolean administrativo;

	@ManyToOne
	@JoinColumn(name = "STH_ID")
	private StatusHospital status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "END_ID")
	private Endereco endereco;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<HospitalCliente> clientes = new ArrayList<>();

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Collection<Contato> contatos = new ArrayList<>();

	public Hospital(Long idHospital, String razaoHospital, String cnpj, Boolean auditavel, StatusHospital status){
		this.setId(idHospital);
		this.setRazaoSocial(razaoHospital);
		this.setCnpj(cnpj);
		this.setAuditavel(auditavel);
		this.setStatus(status);
	}

	public Hospital(Long id, String razao, Municipio municipio, Long idEndereco){
		this.id = id;
		this.razaoSocial = razao;
		this.endereco = new Endereco(idEndereco, municipio);
	}

	public Hospital(Long idHospital, String razaoHospital, String cnpj, String telefone, String email, Boolean auditavel, StatusHospital status, Endereco endereco){
		this.setId(idHospital);
		this.setRazaoSocial(razaoHospital);
		this.setCnpj(cnpj);
		this.setTelefone(telefone);
		this.setEmail(email);
		this.setAuditavel(auditavel);
		this.setStatus(status);
		this.setEndereco(endereco);
	}

	public Hospital(Long id, String razaoSocial){
		this.setId(id);
		this.setRazaoSocial(razaoSocial);
	}

	public Hospital(Long id){
		this.setId(id);
	}

	@Override
	public RestHospital modelParaRest() {
		return HospitalMapper.INSTANCE.convertToRest(this);
	}

	public void addClientes(HospitalCliente cliente)
	{
		if(this.clientes == null)
		{
			this.clientes = new ArrayList<>();
		}

		cliente.setHospital(this);
		this.clientes.add(cliente);
	}

}
