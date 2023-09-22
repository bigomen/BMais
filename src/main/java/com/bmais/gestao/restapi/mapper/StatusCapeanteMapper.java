package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusCapeante;
import com.bmais.gestao.restapi.restmodel.RestStatusCapeante;

@Mapper()
public interface StatusCapeanteMapper {

    StatusCapeanteMapper INSTANCE = Mappers.getMapper(StatusCapeanteMapper.class);

    RestStatusCapeante convertToRest(StatusCapeante statusCapeante);

    StatusCapeante convertToModel(RestStatusCapeante restStatusCapeante);
}
