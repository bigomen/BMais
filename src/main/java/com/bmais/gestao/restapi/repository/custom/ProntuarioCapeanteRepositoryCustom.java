package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeantePesquisa;

import java.util.Collection;
import java.util.Optional;

public interface ProntuarioCapeanteRepositoryCustom {

    Collection<ProntuarioCapeante> pesquisarCapeanteInternacao(RestProntuarioCapeantePesquisa params);
    Optional<ProntuarioCapeante> detalharCapeanteInternacao(Long id);
    Long numeroCapeante();
}
