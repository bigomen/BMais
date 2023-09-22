package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ProntuarioMotivoAlta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProntuarioMotivoAltaRepository extends CrudRepository<ProntuarioMotivoAlta, Long> {

    @Query("select p from ProntuarioMotivoAlta p order by p.descricao")
    Collection<ProntuarioMotivoAlta> lista();
}
