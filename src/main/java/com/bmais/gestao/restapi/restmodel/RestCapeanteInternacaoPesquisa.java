package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.constants.Constantes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCapeanteInternacaoPesquisa
{
	private Long numeroCapeante;
	
	private String paciente;
	
	private String cliente;
	
	private String hospital;
	
	@DateTimeFormat(pattern = Constantes.PATTERN_DATA)
	private LocalDate inicioCobranca;
	
	@DateTimeFormat(pattern = Constantes.PATTERN_DATA)
	private LocalDate fimCobranca;
	
	private String status;
	
	private String senhaInternacao;
	
	private String numeroConta;
	
	private String matriculaPaciente;

	private String internacao;
}
