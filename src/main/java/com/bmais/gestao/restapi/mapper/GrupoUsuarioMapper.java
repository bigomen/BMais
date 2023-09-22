package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.restmodel.RestGrupoUsuario;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class})
public interface GrupoUsuarioMapper {

    GrupoUsuarioMapper INSTANCE = Mappers.getMapper(GrupoUsuarioMapper.class);

    RestGrupoUsuario convertToRest(GrupoUsuario grupoUsuario);

    @Mappings(value = {
    		@Mapping(target = "descricao", ignore = true),
    		@Mapping(target = "permissoes", ignore = true),
    		@Mapping(target = "categoria", ignore = true)
    })
    GrupoUsuario convertToModel(RestGrupoUsuario restGrupoUsuario);
}
