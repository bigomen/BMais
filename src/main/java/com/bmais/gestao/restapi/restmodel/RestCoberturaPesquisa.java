package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCoberturaPesquisa {

    private String tipoAuditor;

    private String auditor;

    private String hospital;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoInicial;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoFinal;

    private String cliente;

    private String servico;
}
