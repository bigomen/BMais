package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.model.enums.TipoLancamento;
import com.bmais.gestao.restapi.restmodel.RestPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface Pagamento
{
    public Long id();
    public LocalDate data();
    public String historico();
    public String documento();
    public BigDecimal valor();
    public TipoLancamento lancamento();
    public BigDecimal saldo();
    public LocalDate noBanco();
    public String movimento();

    public RestPagamento convertToPagamento();
}
