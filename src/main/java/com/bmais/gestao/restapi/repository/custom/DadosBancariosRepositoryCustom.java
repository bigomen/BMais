package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.DadosBancarios;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Optional;

public interface DadosBancariosRepositoryCustom {

    Collection<DadosBancarios> listar(Specification<DadosBancarios> spec);
    Optional<DadosBancarios> pesquisarPorId(Long id);
}
