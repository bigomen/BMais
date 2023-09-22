package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import java.util.Optional;
import com.bmais.gestao.restapi.model.CapeanteInternacao;
import com.bmais.gestao.restapi.model.projections.CapeanteInternacaoProjection;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacaoPesquisa;

public interface CapeanteInternacaoRepositoryCustom
{
	public Collection<CapeanteInternacaoProjection> listaInternacoes(RestCapeanteInternacaoPesquisa params);
	public Optional<CapeanteInternacao> detalharInternacao(Long id);
}
