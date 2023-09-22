package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.repository.custom.MunicipioRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio,Long> , MunicipioRepositoryCustom {

    @Query("select new Municipio(m.id) from Municipio m join m.uf u where m.nome = :municipio and u.sigla = :estado")
    Municipio validarCidade(String municipio, String estado);

    Optional<Municipio> findByNomeAndUfSigla(String nome, String sigla);

    Collection<Municipio> findByUfIdOrderByNome(Long ufId);
}
