package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.utility.BaseRuleUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RestInternacaoPesquisa extends BaseRuleUtil {

    private String nomePaciente;
    private String paciente;
    private String cliente;
    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInicioInternacao;

    @DateTimeFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataFinalInternacao;

    private Long status;
}
