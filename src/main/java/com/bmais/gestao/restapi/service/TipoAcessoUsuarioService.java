package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.TipoAcessoUsuario;
import com.bmais.gestao.restapi.repository.TipoAcessoUsuarioRepository;
import com.bmais.gestao.restapi.restmodel.RestTipoAcessoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TipoAcessoUsuarioService extends com.bmais.gestao.restapi.service.Service<TipoAcessoUsuario, RestTipoAcessoUsuario>{

    private final TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;

    @Autowired
    public TipoAcessoUsuarioService(TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository) {
        super();
        this.tipoAcessoUsuarioRepository = tipoAcessoUsuarioRepository;
    }

    public Collection<RestTipoAcessoUsuario> listar() {
        return tipoAcessoUsuarioRepository.findAllByOrderByDescricaoAsc().stream().map(TipoAcessoUsuario::modelParaRest).collect(Collectors.toList());
    }

    @Override
    protected CrudRepository<TipoAcessoUsuario, Long> getRepository() {
        return tipoAcessoUsuarioRepository;
    }
}
