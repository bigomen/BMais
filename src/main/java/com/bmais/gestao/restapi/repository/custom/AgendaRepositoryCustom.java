package com.bmais.gestao.restapi.repository.custom;

import java.time.LocalDate;
import java.util.Collection;
import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.restmodel.RestAgendaPesquisa;

public interface AgendaRepositoryCustom {

    Collection<Agenda> lista(RestAgendaPesquisa params);
    Boolean existeAgendaParaAuditor(Long auditor, LocalDate data);
}
