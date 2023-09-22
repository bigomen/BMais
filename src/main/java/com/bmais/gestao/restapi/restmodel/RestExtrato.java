package com.bmais.gestao.restapi.restmodel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RestExtrato
{
    private LocalDate data;
    private String historico;
    private String documento;
    private BigDecimal valor;
    private String lancamento;
    private BigDecimal saldo;
    private LocalDate noBanco;
    private String movimento;
}
