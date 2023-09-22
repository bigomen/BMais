package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.enums.TipoLancamento;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RestPagamento
{
    private String id;
    private LocalDate data;
    private String historico;
    private String documento;
    private BigDecimal valor;
    private TipoLancamento lancamento;
    private BigDecimal saldo;
    private LocalDate noBanco;
    private String movimento;
}
