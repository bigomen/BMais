package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.restmodel.RestUsuario;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, StatusUsuarioMapper.class, GrupoUsuarioMapper.class})
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);


    RestUsuario convertToRest(Usuario usuario);

    @Mapping(target = "cliente", qualifiedByName = "restClientToModel")
    Usuario convertToModel(RestUsuario restUsuario);

    @Named(value = "toUsuario")
    default Usuario toUsuario(RestUsuario usuario)
    {
    	if(usuario == null || StringUtils.isBlank(usuario.getId()))
		{
    		return null;
		}

        return new Usuario(UtilSecurity.decryptId(usuario.getId()));
    }

    @Named(value = "restClientToModel")
    default Cliente restClientToModel(RestCliente restCliente){
        if(restCliente.getId() == null){
            return null;
        }
        return new Cliente(UtilSecurity.decryptId(restCliente.getId()));
    }
}
