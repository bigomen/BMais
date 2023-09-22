package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Tuss;
import com.bmais.gestao.restapi.repository.custom.TussRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TussRepository extends CrudRepository<Tuss, Long>, TussRepositoryCustom {

    @Query(value = "select t from Tuss t join fetch t.status s where t.id = :id")
    Optional<Tuss> findById(@Param(value = "id") Long id);

    Boolean existsByCodigo(String codigo);

    @Query("select CASE WHEN count(t.id) > 0 THEN TRUE ELSE FALSE END from Tuss t where t.codigo = :codigo and t.id <> :id")
    Boolean validarCodigoUpdate(String codigo, Long id);

    @Query("select new Tuss(t.id, t.codigo, t.descricao) from Tuss t order by t.descricao")
    Collection<Tuss> listaSimples();
}
