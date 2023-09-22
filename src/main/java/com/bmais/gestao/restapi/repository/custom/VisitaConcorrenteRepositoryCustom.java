package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.model.Visita;
import com.bmais.gestao.restapi.restmodel.RestVisitaConcorrentePesquisa;

import java.util.Collection;

public interface VisitaConcorrenteRepositoryCustom {

    Collection<Visita> lista(RestVisitaConcorrentePesquisa params);

    Visita detalhes(Long id);
    
    Collection<CID> pesquisarUltimoCID(String paciente);
}
