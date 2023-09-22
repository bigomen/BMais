package com.bmais.gestao.restapi.repository.custom;

import java.math.BigDecimal;
import java.util.Collection;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;

public interface MovimentacaoBancariaRepositoryCustom
{
	Collection<MovimentacaoBancaria> pesquisarMovimentacoesBancarias(String dadosBancarios, BigDecimal valor);
}
