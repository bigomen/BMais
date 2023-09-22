package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ConclusaoAuditoriaHC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ConclusaoAuditoriaHCRepository extends CrudRepository<ConclusaoAuditoriaHC, Long> {

    @Query("select new ConclusaoAuditoriaHC(c.id, c.descricao) from ConclusaoAuditoriaHC c order by c.descricao")
    Collection<ConclusaoAuditoriaHC> lista();

    @Query("select new ConclusaoAuditoriaHC(c.id, c.descricao) from ConclusaoAuditoriaHC c join c.auditorias a where a.id = :id order by c.descricao")
    Collection<ConclusaoAuditoriaHC> conclusoesAuditoria(Long id);
}
