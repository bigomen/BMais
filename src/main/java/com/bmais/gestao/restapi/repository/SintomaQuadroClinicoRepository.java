package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.SintomaQuadroClinico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SintomaQuadroClinicoRepository extends CrudRepository<SintomaQuadroClinico, Long> {

    @Query("select new SintomaQuadroClinico(s.id, s.descricao) from SintomaQuadroClinico s order by s.descricao")
    Collection<SintomaQuadroClinico> lista();

    @Query("select new SintomaQuadroClinico(s.id, s.descricao)from SintomaQuadroClinico s join s.auditorias a where a.id = :id order by s.descricao")
    Collection<SintomaQuadroClinico> sintomasAuditoria(Long id);
}
