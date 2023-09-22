package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestEscalaPesquisa
{
    private String cliente;

    private String hospital;

    private String auditor;

    private String servico;
    
    private String tipoAuditor;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicio;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFim;
}
