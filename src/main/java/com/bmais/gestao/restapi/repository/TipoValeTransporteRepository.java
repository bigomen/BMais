package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.TipoValeTransporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TipoValeTransporteRepository extends CrudRepository<TipoValeTransporte, Long> {

    Collection<TipoValeTransporte> findAllByOrderByDescricaoAsc();
}
