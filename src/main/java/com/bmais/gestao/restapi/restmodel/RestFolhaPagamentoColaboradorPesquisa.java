package com.bmais.gestao.restapi.restmodel;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
public class RestFolhaPagamentoColaboradorPesquisa {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoVencimentoInicial;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoVencimentoFinal;

    private String colaboradores;

    private String tipoPagamento;
}
