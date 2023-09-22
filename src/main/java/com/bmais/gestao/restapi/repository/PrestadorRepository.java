package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.repository.custom.PrestadorRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PrestadorRepository extends CrudRepository<Prestador, Long>, PrestadorRepositoryCustom {
    @Query("select new Prestador(p.id, p.razaoSocial) from Prestador p join p.status s where s.id = 1L order by p.razaoSocial")
    Collection<Prestador> listaSimples();

    @Query("select new Prestador(p.id) from Prestador p join p.status s where s.id = 1L and p.razaoSocial = :razao")
    Prestador existsByRazaoSocial(String razao);

    @Query("select new Prestador(p.id) from Prestador p join p.status s where s.id = 1L and p.cnpj = :cnpj")
    Prestador existsByCnpj(String cnpj);

    @Query("select CASE WHEN count(p.id) > 0 THEN true ELSE false END from Prestador p where (p.cnpj = :cnpj or p.razaoSocial = :razaoSocial) and p.id <> :id")
    Boolean validarCnpj(String cnpj, String razaoSocial, Long id);
}
