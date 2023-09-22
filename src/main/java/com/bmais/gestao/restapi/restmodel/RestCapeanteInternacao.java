package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import com.bmais.gestao.restapi.mapper.CapeanteInternacaoMapper;
import com.bmais.gestao.restapi.model.CapeanteInternacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestCapeanteInternacao extends RestModel<CapeanteInternacao> implements Serializable {

    private static final long serialVersionUID = 1L;
    
	@JsonProperty(value = "usuario")
	private String usuario;
	
    @JsonProperty(value = "prontuario")
    private RestProntuarioCapeante prontuario;

	@JsonProperty(value = "capeante_internado")
	private RestCapeanteInternado internado;
	
	@JsonProperty(value = "diarias")
	private RestDiariaCapeanteInternacao diarias;
	
	@JsonProperty(value = "procedimentos_realizados")
	private Collection<RestProcedimentoCapeante> procedimentos;
	
	@JsonProperty(value = "resumo")
	private RestResumoCapeanteInternacao resumo;
	
	@JsonProperty(value = "valorApresentado")
	private BigDecimal valorApresentado;
	
	@JsonProperty(value = "valorGlosado")
	private BigDecimal valorGlosado;
	
	@JsonProperty(value = "valorLiberado")
	private BigDecimal valorLiberado;
	
	
	@Override
	public CapeanteInternacao restParaModel()
	{
		return CapeanteInternacaoMapper.INSTANCE.convertRestToModel(this);
	}
}
