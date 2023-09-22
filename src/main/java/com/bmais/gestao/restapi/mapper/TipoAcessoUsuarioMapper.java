package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoAcessoUsuario;
import com.bmais.gestao.restapi.restmodel.RestTipoAcessoUsuario;

@Mapper()
public interface TipoAcessoUsuarioMapper {

    TipoAcessoUsuarioMapper INSTANCE = Mappers.getMapper(TipoAcessoUsuarioMapper.class);

    RestTipoAcessoUsuario convertToRest(TipoAcessoUsuario tipoAcessoUsuario);

    TipoAcessoUsuario convertToModel(RestTipoAcessoUsuario restTipoAcessoUsuario);
}
