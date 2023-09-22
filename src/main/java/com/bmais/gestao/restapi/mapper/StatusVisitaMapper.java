package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusVisita;
import com.bmais.gestao.restapi.restmodel.RestStatusVisita;

@Mapper()
public interface StatusVisitaMapper {

    StatusVisitaMapper INSTANCE = Mappers.getMapper(StatusVisitaMapper.class);

    RestStatusVisita convertToRest(StatusVisita statusVisita);

    StatusVisita convertToModel(RestStatusVisita restStatusVisita);
}
