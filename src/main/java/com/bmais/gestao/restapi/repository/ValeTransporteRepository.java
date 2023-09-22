package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ValeTransporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ValeTransporteRepository extends CrudRepository<ValeTransporte, Long> {

    @Query("select new ValeTransporte(v.id, v.data, v.quantidade, v.tipo, v.observacao) from ValeTransporte v where v.colaborador.id = :id order by v.data")
    Collection<ValeTransporte> valesColaborador(Long id);
}
