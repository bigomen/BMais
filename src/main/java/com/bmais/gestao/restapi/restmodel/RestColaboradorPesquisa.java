package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.model.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestColaboradorPesquisa {

    private String nome;
    private Sexo sexo;
    private String status;
    private String cargo;
    private Integer idade;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate inicioContratacao;
    
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate fimContratacao;
}
