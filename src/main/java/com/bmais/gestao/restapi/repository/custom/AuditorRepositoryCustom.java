package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import java.util.Optional;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.restmodel.RestCoberturaPesquisa;

public interface AuditorRepositoryCustom
{
    Optional<Auditor> pesquisarAuditor(Long id);

    Collection<Auditor> findByPrestador(Prestador fornecedorAuditoria);

    Collection<Auditor> listaAuditorCobertura(RestCoberturaPesquisa params);
    
    Collection<Auditor> pesquisarAuditorPorHospital(String hospital);
}
