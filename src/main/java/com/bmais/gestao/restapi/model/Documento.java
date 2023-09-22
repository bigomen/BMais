package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.DocumentoMapper;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "DOCUMENTO")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Documento extends Model<RestDocumento> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTO")
	@SequenceGenerator(name = "SEQ_DOCUMENTO", sequenceName = "SEQ_DOCUMENTO", allocationSize = 1)
	@EqualsAndHashCode.Include
	@Column(name = "DOC_ID")
	private Long id;
	
	@Column(name = "DOC_DESCRICAO")
	private String descricao;
	
	@Column(name = "DOC_VALIDADE")
	private LocalDate validade;
	
	@Column(name = "DOC_TIPO_DOCUMENTO")
	private String tipo;

	@Transient
	private String imagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_ID")
	private PessoaJuridica pessoaJuridica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ID")
	private Colaborador colaborador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUD_ID")
	private Auditor auditor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AFA_ID")
	private Afastamento afastamento;

	public Documento(Long id, String descricao, LocalDate validade, String tipo){
		this.setId(id);
		this.setDescricao(descricao);
		this.setValidade(validade);
		this.setTipo(tipo);
	}

	@Override
	public RestDocumento modelParaRest(){
		return DocumentoMapper.INSTANCE.convertToRest(this);
	}
}
