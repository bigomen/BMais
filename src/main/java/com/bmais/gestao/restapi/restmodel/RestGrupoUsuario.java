package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.GrupoUsuarioMapper;
import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestGrupoUsuario extends RestModel<GrupoUsuario> implements Serializable {

	private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "categoria")
    private CategoriaGrupoUsuario categoria;

    @Override
    public GrupoUsuario restParaModel() {
        return GrupoUsuarioMapper.INSTANCE.convertToModel(this);
    }
}
