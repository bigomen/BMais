package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.TipoAcessoUsuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TipoAcessoUsuarioRepository extends CrudRepository<TipoAcessoUsuario, Long> {

    Collection<TipoAcessoUsuario> findAllByOrderByDescricaoAsc();
}
