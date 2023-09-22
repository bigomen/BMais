package com.bmais.gestao.restapi.model.projections;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.bmais.gestao.restapi.mapper.CapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
import lombok.Getter;

@Getter
public class CapeantePSAmbulatorioProjection
{
	private Long idCapeante;
	private String hospital;
	private String cliente;
	private String protocolo;
	private String status;
	private LocalDate dataFechamento;
    private BigDecimal valorCobrado;
    private BigDecimal valorGlosado;
    private BigDecimal valorLibarado;
    
    
	public CapeantePSAmbulatorioProjection(Long idCapeante, String hospital,
				String cliente, String protocolo, String status,
				LocalDate dataFechamento, BigDecimal valorCobrado,
				BigDecimal valorGlosado, BigDecimal valorLibarado)
		{
			super();
			this.idCapeante = idCapeante;
			this.hospital = hospital;
			this.cliente = cliente;
			this.protocolo = protocolo;
			this.status = status;
			this.dataFechamento = dataFechamento;
			this.valorCobrado = valorCobrado;
			this.valorGlosado = valorGlosado;
			this.valorLibarado = valorLibarado;
		}
	
	public RestCapeantePSAmbulatorio projectionToRest()
	{
		return CapeantePSAmbulatorioMapper.INSTANCE.projectionToRest(this);
	}
}
