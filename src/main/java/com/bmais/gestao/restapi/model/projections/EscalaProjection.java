package com.bmais.gestao.restapi.model.projections;

import java.time.LocalDate;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.StatusAuditor;
import com.bmais.gestao.restapi.model.TipoAuditor;
import lombok.Data;

@Data
public class EscalaProjection
{
	private Long idEscala;
	private String cliente;
	private String hospital;
	private Servico servico;
	private Auditor auditor;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Cobertura cobertura;
	
	public EscalaProjection(Long id, String cliente, String hospital, String codServico, String servico, 
			String nomeAuditor, Long idTipoAuditor, String tipoAuditor, String emailAuditor, String documentoAuditor, String statusAuditor, LocalDate dataInicio, LocalDate dataFim,
			Long idCobertura, LocalDate inicioCobertura, LocalDate fimCobertura, String obsCobertura, Long idAuditorCobertura, String auditorCobertura, String emailAuditorCobertura, String docAuditorCobertura)
	{
		super();
		this.idEscala = id;
		this.cliente = cliente;
		this.hospital = hospital;
		this.servico = new Servico(null, servico, codServico);
		this.auditor = new Auditor(null, nomeAuditor, new TipoAuditor(idTipoAuditor, tipoAuditor), documentoAuditor, emailAuditor, null, null, new StatusAuditor(statusAuditor));
		this.cobertura = new Cobertura();
		this.cobertura.setId(idCobertura);
		this.cobertura.setPeriodoInicio(inicioCobertura);
		this.cobertura.setPeriodoFim(fimCobertura);
		this.cobertura.setObservacao(obsCobertura);
		this.cobertura.setAuditor(new Auditor(idAuditorCobertura, auditorCobertura, null, docAuditorCobertura, emailAuditorCobertura, null, null, null));
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	public EscalaProjection(Long id, String cliente, String hospital, String codServico, String servico, 
			String nomeAuditor, Long idTipoAuditor, String tipoAuditor, String emailAuditor, String documentoAuditor, String statusAuditor, LocalDate dataInicio, LocalDate dataFim)
	{
		super();
		this.idEscala = id;
		this.cliente = cliente;
		this.hospital = hospital;
		this.servico = new Servico(null, servico, codServico);
		this.auditor = new Auditor(null, nomeAuditor, new TipoAuditor(idTipoAuditor, tipoAuditor), documentoAuditor, emailAuditor, null, null, new StatusAuditor(statusAuditor));
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
}
