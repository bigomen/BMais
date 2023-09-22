package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.StatusFerias;
import com.bmais.gestao.restapi.restmodel.RestStatusFerias;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusFeriasMapper {

    StatusFeriasMapper INSTANCE = Mappers.getMapper(StatusFeriasMapper.class);

    RestStatusFerias convertToRest(StatusFerias statusFerias);

    StatusFerias convertToModel(RestStatusFerias restStatusFerias);
}
