package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoAcessoUsuarioMapper;
import com.bmais.gestao.restapi.model.TipoAcessoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoAcessoUsuario extends RestModel<TipoAcessoUsuario> implements Serializable {

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoAcessoUsuario restParaModel() {
        return TipoAcessoUsuarioMapper.INSTANCE.convertToModel(this);
    }
}
