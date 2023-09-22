package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.restmodel.RestUsuarioPesquisa;

import java.util.Collection;

public interface UsuarioRepositoryCustom {

    Collection<Usuario> lista(RestUsuarioPesquisa params);

    Usuario detalhes(Long id);
}
