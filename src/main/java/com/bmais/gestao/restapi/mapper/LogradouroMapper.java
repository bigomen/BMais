package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Logradouro;
import com.bmais.gestao.restapi.restmodel.RestLogradouro;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface LogradouroMapper {
    LogradouroMapper INSTANCE = Mappers.getMapper(LogradouroMapper.class);

    RestLogradouro convertToRest(Logradouro logradouro);

    Logradouro convertToModel(RestLogradouro restLogradouro);
}
