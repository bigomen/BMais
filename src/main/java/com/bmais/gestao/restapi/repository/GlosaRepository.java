package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.repository.custom.GlosaRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GlosaRepository extends CrudRepository<Glosa, Long>, GlosaRepositoryCustom {

    @Query("select g from Glosa g where g.codigo = ?1")
    Glosa findByCodigo(String codigo);

    @Query("select CASE WHEN count(g.id) > 0 THEN true ELSE false END from Glosa g where g.codigo = :codigo and g.id <> :id")
    Boolean validarCodigo(String codigo, Long id);

    @Query(value = "select g from Glosa g join fetch g.status s where g.id = :id")
    Optional<Glosa> findById(@Param(value = "id") Long id);
}
