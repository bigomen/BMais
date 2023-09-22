package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.GlosaMapper;
import com.bmais.gestao.restapi.model.enums.Responsabilidade;
import com.bmais.gestao.restapi.restmodel.RestGlosa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_GLOSA_CRUD")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Glosa extends Model<RestGlosa> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_GLOSA")
	@SequenceGenerator(name = "SEQ_TIPO_GLOSA", sequenceName = "SEQ_TIPO_GLOSA_CRUD", allocationSize = 1)
	@Column(name = "TGC_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "TGC_CODIGO")
	private String codigo;

	@Column(name = "TGC_RESPONSABILIDADE")
	@Enumerated(EnumType.STRING)
	private Responsabilidade responsabilidade;

	@Column(name = "TGC_ABRIR_DIARIA")
	private Boolean abrirDiaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STG_ID")
	private StatusGlosa status;

	@Column(name = "TGC_DESCRICAO")
	private String descricao;

	@PrePersist
	private void preInsert()
	{
		this.status = new StatusGlosa(StatusGlosa.ATIVO);
	}

	@Override
	public RestGlosa modelParaRest() {
		return GlosaMapper.INSTANCE.convertToRest(this);
	}
}
