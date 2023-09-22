package com.bmais.gestao.restapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.model.Perfil;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Long>{
	
	
}
