package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusGlosa;
import com.bmais.gestao.restapi.restmodel.RestStatusGlosa;

@Mapper()
public interface StatusGlosaMapper {

    StatusGlosaMapper INSTANCE = Mappers.getMapper(StatusGlosaMapper.class);

    RestStatusGlosa convertToRest(StatusGlosa statusGlosa);

    StatusGlosa convertToModel(RestStatusGlosa restStatusGlosa);
}
