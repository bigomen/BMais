package com.bmais.gestao.restapi.model.projections;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.bmais.gestao.restapi.mapper.CapeanteInternacaoMapper;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CapeanteInternacaoProjection
{
	private Long id;
	private LocalDate inicioCobranca;
	private Long idProntuario;
	private String senhaInternacao;
	private String paciente;
	private String matricula;
	private String hospital;
	private String cliente;
	private String status;
	private BigDecimal valorApresentado;
	private BigDecimal valorGlosado;
	private BigDecimal valorLiberado;
	private String nomeMedico;
	private String nomeEnfermeiro;
	private String observacao;
//	private Auditor medico;
//	private Auditor enfermeiro;
//	private String observacao;
	
	public RestCapeanteInternacao projectionToRest()
	{
		return CapeanteInternacaoMapper.INSTANCE.convertProjectionToRest(this);
	}
}
