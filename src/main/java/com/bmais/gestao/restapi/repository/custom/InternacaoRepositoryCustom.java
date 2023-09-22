package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Internacao;
import com.bmais.gestao.restapi.restmodel.RestInternacaoPesquisa;

import java.util.Collection;
import java.util.List;

public interface InternacaoRepositoryCustom {

    List<Internacao> pesquisar(Long aLong, Long decryptId);

    Collection<Internacao> lista(RestInternacaoPesquisa params);
    
    Internacao detalhes(Long id);

    Long numeroInternacao();
}
