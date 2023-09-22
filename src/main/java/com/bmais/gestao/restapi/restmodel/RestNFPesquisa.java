package com.bmais.gestao.restapi.restmodel;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RestNFPesquisa {

    private String numeroNota;
    private String empresa;
    private String cliente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicioEmissao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinalEmissao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicioVencimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinalVencimento;

    private String servico;
    private String status;
}
