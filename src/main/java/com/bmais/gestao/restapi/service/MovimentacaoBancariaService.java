package com.bmais.gestao.restapi.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;
import com.bmais.gestao.restapi.model.TipoMovimentacaoBancaria;
import com.bmais.gestao.restapi.repository.MovimentacaoBancariaRepository;
import com.bmais.gestao.restapi.repository.TipoMovimentacaoBancariaRepository;
import com.bmais.gestao.restapi.restmodel.RestMovimentacaoBancaria;
import com.bmais.gestao.restapi.restmodel.RestTipoMovimentacaoBancaria;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class MovimentacaoBancariaService extends Service<MovimentacaoBancaria, RestMovimentacaoBancaria>
{
	private final MovimentacaoBancariaRepository movimentacaoBancariarepository;
	private final TipoMovimentacaoBancariaRepository tipoMovimentacaoBancariaRepository;
	
	@Autowired
	public MovimentacaoBancariaService(
			MovimentacaoBancariaRepository movimentacaoBancariarepository, TipoMovimentacaoBancariaRepository tipoMovimentacaoBancariaRepository)
	{
		super();
		this.movimentacaoBancariarepository = movimentacaoBancariarepository;
		this.tipoMovimentacaoBancariaRepository = tipoMovimentacaoBancariaRepository;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void nova(RestMovimentacaoBancaria restMovimentacaoBancaria)
	{
		MovimentacaoBancaria movimentacaoBancaria = restMovimentacaoBancaria.restParaModel();
		
		validarMovimentacao(movimentacaoBancaria);
		movimentacaoBancariarepository.save(movimentacaoBancaria);
	}

	public Collection<RestMovimentacaoBancaria> lista(String dadosBancarios,
			BigDecimal valor)
	{
		Collection<MovimentacaoBancaria> movimentacoesBancarias = movimentacaoBancariarepository.pesquisarMovimentacoesBancarias(dadosBancarios, valor);
		return movimentacoesBancarias.stream().map(MovimentacaoBancaria::modelParaRest).collect(Collectors.toList());
	}

	public Collection<RestTipoMovimentacaoBancaria> listaTiposMovimentacao()
	{
		Iterable<TipoMovimentacaoBancaria> all = tipoMovimentacaoBancariaRepository.findAllByOrderByDescricaoAsc();
		
		return StreamSupport.stream(all.spliterator(), false)
				.map(TipoMovimentacaoBancaria::modelParaRest)
				.sorted(Comparator.comparing(RestTipoMovimentacaoBancaria::getDescricao))
				.collect(Collectors.toList());
	}

	@Override
	protected CrudRepository<MovimentacaoBancaria, Long> getRepository()
	{
		return movimentacaoBancariarepository;
	}

	private void validarMovimentacao(MovimentacaoBancaria movimentacaoBancaria)
	{
		if(movimentacaoBancaria.ehMovimentacaoEntreContas() && movimentacaoBancaria.getContaOrigem().equals(movimentacaoBancaria.getContaDestino()))
		{
			throw new RuntimeException("Conta de origem e destino não podem ser iguais para movimentação");
		}
	}

}
