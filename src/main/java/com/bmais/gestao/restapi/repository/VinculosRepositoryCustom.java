package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import com.bmais.gestao.restapi.model.projections.EscalaProjection;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;

public interface VinculosRepositoryCustom
{
	public Collection<EscalaProjection> pesquisarEscalas(RestEscalaPesquisa param);
	
	public Collection<EscalaProjection> pesquisarCoberturas(RestEscalaPesquisa param);
}
