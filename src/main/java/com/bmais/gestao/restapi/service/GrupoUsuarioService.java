package com.bmais.gestao.restapi.service;


import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.repository.GrupoUsuarioRepository;
import com.bmais.gestao.restapi.restmodel.RestGrupoUsuario;
import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GrupoUsuarioService extends com.bmais.gestao.restapi.service.Service<GrupoUsuario, RestGrupoUsuario> {

    private final GrupoUsuarioRepository grupoUsuarioRepository;

    Logger logger;

    public GrupoUsuarioService(GrupoUsuarioRepository grupoUsuarioRepository) {
        this.grupoUsuarioRepository = grupoUsuarioRepository;
    }

    public Collection<RestGrupoUsuario> listar(){
        Collection<GrupoUsuario> grupoUsuarios = grupoUsuarioRepository.listar(GrupoUsuario.AUDITOR_MEDICO, GrupoUsuario.AUDITOR_ENFERMEIRO);
        return grupoUsuarios.stream().map(GrupoUsuario::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestGrupoUsuario> listaSimples(){
        Collection<GrupoUsuario> grupoUsuarios = grupoUsuarioRepository.listaSimples(GrupoUsuario.AUDITOR_MEDICO, GrupoUsuario.AUDITOR_ENFERMEIRO);
        return grupoUsuarios.stream().map(GrupoUsuario::modelParaRest).collect(Collectors.toList());
    }

    public GrupoUsuario getGrupo(Long id){
        GrupoUsuario grupo = grupoUsuarioRepository.getGrupo(id);
        if(grupo == null){
            throw new ObjectNotFoundException(MensagensID.UsuarioNaoEncontrado);
        }
        return grupo;
    }

    @Override
    protected CrudRepository<GrupoUsuario, Long> getRepository(){return grupoUsuarioRepository;}
}
