package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Beneficio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BeneficioRepository extends CrudRepository<Beneficio, Long> {

    @Query("select new Beneficio(b.id, b.descricao) from Beneficio b order by b.descricao")
    Collection<Beneficio> listaSimples();

    Boolean existsByDescricao(String descricao);

}
