package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusUsuario;
import com.bmais.gestao.restapi.restmodel.RestStatusUsuario;

@Mapper()
public interface StatusUsuarioMapper {

    StatusUsuarioMapper INSTANCE = Mappers.getMapper(StatusUsuarioMapper.class);

    RestStatusUsuario convertToRest(StatusUsuario statusUsuario);

    StatusUsuario convertToModel(RestStatusUsuario restStatusUsuario);
}
