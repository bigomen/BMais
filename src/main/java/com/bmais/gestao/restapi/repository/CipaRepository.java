package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Cipa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CipaRepository extends CrudRepository<Cipa, Long> {

    @Query("select new Cipa(c.id, c.inicioGestao, c.fimGestao, c.inicioEstabilidade, c.fimEstabilidade, c.cadastro, c.tipo, u.id, u.email) from Cipa c join c.usuario u where c.colaborador.id = :id order by c.inicioGestao")
    Collection<Cipa> cipasColaborador(Long id);

    @Query("select u.id from Cipa c join c.usuario u where c.id = :id")
    Long getUsuario(Long id);
}
