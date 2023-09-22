package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Escolaridade;
import com.bmais.gestao.restapi.restmodel.RestEscolaridade;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface EscolaridadeMapper {

    EscolaridadeMapper INSTANCE = Mappers.getMapper(EscolaridadeMapper.class);

    RestEscolaridade convertToRest(Escolaridade escolaridade);

    Escolaridade convertToModel(RestEscolaridade restEscolaridade);
}
