package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.constants.Constantes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestProntuarioCapeantePesquisa 
{
    private String paciente;
    private String cliente;
    private String hospital;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInicioInternacao;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataFimInternacao;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInicioAlta;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataFimAlta;
    
    private String status;
}
