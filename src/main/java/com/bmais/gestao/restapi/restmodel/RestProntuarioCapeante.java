package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import com.bmais.gestao.restapi.mapper.ProntuarioCapeanteMapper;
import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.model.enums.TipoAlta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestProntuarioCapeante extends RestModel<ProntuarioCapeante> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "numero")
	private Long numero;

	@JsonProperty(value = "contaHospitalar")
	private String contaHospitalar;

	@JsonProperty(value = "tipoAlta")
	private TipoAlta tipoAlta;

	@JsonProperty(value = "dataInternacao")
	private LocalDate dataInternacao;

	@JsonProperty(value = "dataAlta")
	private LocalDate dataAlta;

	@JsonProperty(value = "senhaInternacao")
	private String senhaInternacao;

	@JsonProperty(value = "senhaConvenio")
	private String senhaConvenio;

	@JsonProperty(value = "obito")
	private boolean obito = false;

	@JsonProperty(value = "relatorio")
	private boolean relatorio = false;

	@JsonProperty(value = "observacao")
	private String observacao;

	@JsonProperty(value = "paciente")
	private RestPaciente paciente;

	@JsonProperty(value = "hospital")
	private RestHospitalCliente hospital;

	@JsonProperty(value = "status")
	private RestStatusCapeante status;

	@JsonProperty(value = "usuario")
	private String usuario;

    public ProntuarioCapeante restParaModel()
    {
        return ProntuarioCapeanteMapper.INSTANCE.convertToModel(this);
    }
}
