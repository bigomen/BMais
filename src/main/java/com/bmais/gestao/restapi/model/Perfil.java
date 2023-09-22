package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.bmais.gestao.restapi.base.BaseModel;

@Entity
@Table(name = "PERFIL")
public class Perfil /*extends BaseModel*/ implements Serializable {

	@Id
	@SequenceGenerator(name = "SQ_PERFIL", sequenceName = "SQ_PERFIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERFIL")
	@Column(name = "AEN_ID", nullable = false)
	private Long id;

	@Column(name = "AEN_DESCRICAO", length = 120)
	private String aenDescricao;

    @Column(name = "AEN_ROLE", length = 120)
	private String aenRole;

//	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
//			CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "perfis")
//	private List<GrupoUsuario> gruposUsuario = new ArrayList();

    
//	public List<GrupoUsuario> getGruposUsuario() {
//		return gruposUsuario;
//	}

//	public void setGruposUsuario(List<GrupoUsuario> gruposUsuario) {
//		this.gruposUsuario = gruposUsuario;
//	}

	public String getAenDescricao() {
		return aenDescricao;
	}

	public void setAenDescricao(String aenDescricao) {
		this.aenDescricao = aenDescricao;
	}

    public String getAenRole() {
        return aenRole;
    }

    public void setAenRole(String aenRole) {
        this.aenRole = aenRole;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public void addGruposUsuario(GrupoUsuario gu) {
//		gruposUsuario.add(gu);
//		gu.getPerfis().add(this);
//	}
//
//	public void removeGruposUsuario(GrupoUsuario gu) {
//		gruposUsuario.remove(gu);
//		gu.getPerfis().remove(this);
//	}

}
