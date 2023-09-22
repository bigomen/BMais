package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.utility.BaseRuleUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RestVisitaConcorrentePesquisa extends BaseRuleUtil {

    private String internacao;
	
    private String paciente;

    private String matriculaPaciente;

    private String cliente;

    private String hospital;

    private String senhaConvenio;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoInicial;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoFinal;

    private String status;
}
