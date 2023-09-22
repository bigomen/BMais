package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Curativo;
import com.bmais.gestao.restapi.restmodel.RestCurativo;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface CurativoMapper {

    CurativoMapper INSTANCE = Mappers.getMapper(CurativoMapper.class);

    RestCurativo convertToRest(Curativo curativo);

    @Mappings(value = {
            @Mapping(target = "visita", ignore = true)
    })
    Curativo convertToModel(RestCurativo restCurativo);
}
