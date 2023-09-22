package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestUsuarioPesquisa {

    private String nome;
    private String perfil;
    private String empresa;
    private String email;
    private CategoriaGrupoUsuario categoria;
}
