package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCarePesquisa;

public interface ProntuarioVisitaHomeCareRepositoryCustom {

    List<ProntuarioVisitaHomeCare> pesquisar(Long aLong, Long decryptId);

    Collection<ProntuarioVisitaHomeCare> lista(RestProntuarioVisitaHomeCarePesquisa params);
    
    Optional<ProntuarioVisitaHomeCare> pesquisarUltimoProntuarioPaciente(Paciente paciente);
    ProntuarioVisitaHomeCare detalhes(Long id);

    Long numeroProntuario();
}
