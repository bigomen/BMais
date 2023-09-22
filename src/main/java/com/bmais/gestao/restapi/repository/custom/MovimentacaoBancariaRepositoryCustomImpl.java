package com.bmais.gestao.restapi.repository.custom;

import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.model.DadosBancarios_;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria_;
import com.bmais.gestao.restapi.utility.UtilSecurity;

public class MovimentacaoBancariaRepositoryCustomImpl extends Repository<MovimentacaoBancaria>
		implements MovimentacaoBancariaRepositoryCustom
{
	@Override
	public 	Collection<MovimentacaoBancaria> pesquisarMovimentacoesBancarias(String dadosBancarios, BigDecimal valor)
	{
		CriteriaQuery<MovimentacaoBancaria> criteria = super.getCriteria();
		Root<MovimentacaoBancaria> root = criteria.from(getClazz());
		root.fetch(MovimentacaoBancaria_.TIPO, JoinType.INNER);
		root.fetch(MovimentacaoBancaria_.USUARIO, JoinType.INNER);
		Predicate conjunction = builder().conjunction();
		
		if(StringUtils.isNotBlank(dadosBancarios))
		{
			Path<Long> contaOrigem = root.get(MovimentacaoBancaria_.CONTA_ORIGEM).get(DadosBancarios_.ID);
			Path<Long> contaDestino = root.get(MovimentacaoBancaria_.CONTA_DESTINO).get(DadosBancarios_.ID);
			
			Predicate equalOrigem = builder().equal(contaOrigem, UtilSecurity.decryptId(dadosBancarios));
			Predicate equalDestino = builder().equal(contaDestino, UtilSecurity.decryptId(dadosBancarios));
			
			Predicate disjunction = builder().disjunction();
			
			disjunction = builder().or(equalOrigem, equalDestino);
			conjunction = builder().and(conjunction, disjunction);
		}
		
		if(valor != null)
		{
			Path<BigDecimal> pathValor = root.get(MovimentacaoBancaria_.VALOR);
			Predicate equalValor = builder().equal(pathValor, valor);
			conjunction = builder().and(conjunction, equalValor);
		}
		
		criteria.where(conjunction);
		criteria.orderBy(builder().asc(root.get("data")));
		TypedQuery<MovimentacaoBancaria> typedQuery = super.entityManager.createQuery(criteria);
		return typedQuery.getResultList();
	}

	@Override
	public Class<MovimentacaoBancaria> getClazz()
	{
		// TODO Auto-generated method stub
		return MovimentacaoBancaria.class;
	}

}
