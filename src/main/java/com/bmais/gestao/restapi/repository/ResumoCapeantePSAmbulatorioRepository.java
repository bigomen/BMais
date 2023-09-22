package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ResumoCapeantePSAmbulatorio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ResumoCapeantePSAmbulatorioRepository extends CrudRepository<ResumoCapeantePSAmbulatorio, Long> {

    @Query("select r from ResumoCapeantePSAmbulatorio r join r.capeantePSAmbulatorio c where c.id = :id order by r.paciente")
    Collection<ResumoCapeantePSAmbulatorio> getResumos(Long id);
}
