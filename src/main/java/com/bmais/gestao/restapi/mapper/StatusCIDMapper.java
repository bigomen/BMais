package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusCID;
import com.bmais.gestao.restapi.restmodel.RestStatusCID;

@Mapper()
public interface StatusCIDMapper {

    StatusCIDMapper INSTANCE = Mappers.getMapper(StatusCIDMapper.class);

    RestStatusCID convertToRest(StatusCID statusCID);

    StatusCID convertToModel(RestStatusCID restStatusCID);
}
