package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
import com.bmais.gestao.restapi.model.projections.CapeantePSAmbulatorioProjection;
import com.bmais.gestao.restapi.restmodel.RestCapeantePesquisa;

import java.util.Collection;
import java.util.Optional;

public interface CapeantePSAmbulatorioRepositoryCustom {

    Collection<CapeantePSAmbulatorioProjection> lista(RestCapeantePesquisa params);

    Optional<CapeantePSAmbulatorio> detalhes(Long id);
}
