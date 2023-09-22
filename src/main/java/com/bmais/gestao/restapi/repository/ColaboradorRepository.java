package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.model.Ferias;
import com.bmais.gestao.restapi.repository.custom.ColaboradorRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, Long> , ColaboradorRepositoryCustom {

    boolean existsByCpf(String cpf);

    @Modifying
    @Query("update Colaborador c set c.status.id = 3L where c.id = :id")
    void desativar(Long id);

    @Query("select new Colaborador(c.id, c.nome) from Colaborador c where c.status.id = 1L and c.operacional = true order by c.nome")
    Collection<Colaborador> listaAdministrativo();

    @Query("select new Colaborador(c.id, c.nome) from Colaborador c where c.status.id = 1L order by c.nome")
    Collection<Colaborador> listaSimples();

    @Query("select v.data from ValeTransporte v where v.id = :id")
    LocalDate getData(Long id);

    @Query("select f from Colaborador c join c.ferias f where f.id = :id")
    Ferias getFerias(Long id);

    @Query("select c.foto from Colaborador c where c.id = :id")
    String getFoto(Long id);

    @Query("select CASE WHEN count(c.id) > 0 THEN true ELSE false END from Colaborador c where c.cpf = :cpf and c.id <> :id")
    Boolean validarCpf(String cpf, Long id);
}
