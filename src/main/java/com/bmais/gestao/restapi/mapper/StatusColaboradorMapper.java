package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusColaborador;
import com.bmais.gestao.restapi.restmodel.RestStatusColaborador;

@Mapper()
public interface StatusColaboradorMapper {

    StatusColaboradorMapper INSTANCE = Mappers.getMapper(StatusColaboradorMapper.class);

    RestStatusColaborador convertToRest(StatusColaborador statusColaborador);

    StatusColaborador convertToModel(RestStatusColaborador restStatusColaborador);
}
