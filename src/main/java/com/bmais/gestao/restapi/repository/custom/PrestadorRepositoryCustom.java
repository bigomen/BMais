package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.restmodel.RestPrestadorPesquisa;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface PrestadorRepositoryCustom {
    Optional<Prestador> detalhes(Long id);
    public Collection<Prestador> pesquisarPrestador(RestPrestadorPesquisa params);
}
