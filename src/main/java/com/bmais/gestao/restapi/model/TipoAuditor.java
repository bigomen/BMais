package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoAuditorMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoAuditor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_AUDITOR")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class TipoAuditor extends Model<RestTipoAuditor> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final long MEDICO = 1L;
	public static final long ENFERMEIRO = 2L;

	@Id
	@Column(name = "TPA_ID")
	private Long id;
	
	@EqualsAndHashCode.Include
	@Column(name = "TPA_DESCRICAO")
	private String descricao;

	public TipoAuditor(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public RestTipoAuditor modelParaRest(){return TipoAuditorMapper.INSTANCE.convertToRest(this);}

}
