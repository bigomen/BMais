package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoGlosa;
import com.bmais.gestao.restapi.restmodel.RestTipoGlosa;

@Mapper()
public interface TipoGlosaMapper {

    TipoGlosaMapper INSTANCE = Mappers.getMapper(TipoGlosaMapper.class);

    RestTipoGlosa convertToRest(TipoGlosa tipoGlosa);

    TipoGlosa convertToModel(RestTipoGlosa restTipoGlosa);
}
