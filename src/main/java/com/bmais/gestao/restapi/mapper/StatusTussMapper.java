package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusTuss;
import com.bmais.gestao.restapi.restmodel.RestStatusTuss;

@Mapper()
public interface StatusTussMapper {

    StatusTussMapper INSTANCE = Mappers.getMapper(StatusTussMapper.class);

    RestStatusTuss convertToRest(StatusTuss statusTuss);

    StatusTuss convertToModel(RestStatusTuss restStatusTuss);
}
