package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import java.util.Optional;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomecarePesquisa;

public interface VisitaHomecareRepositoryCustom {

    Collection<VisitaHomeCare> lista(RestVisitaHomecarePesquisa params);
    
    Optional<VisitaHomeCare> pesquisarUltimaVisitaProntuario(ProntuarioVisitaHomeCare prontuario);

    VisitaHomeCare detalhes(Long id);

    Long numeroVisita();
}
