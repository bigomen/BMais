package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.utility.BaseRuleUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RestProntuarioVisitaHomeCarePesquisa extends BaseRuleUtil {

    private String paciente;

    private String cliente;

    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate inicio;

    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate fim;

    private String status;
    
    private String empresa;
    
    private String hospital;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate altaInicio;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate altaFim;
}
