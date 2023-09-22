package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.restmodel.RestGlosa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, StatusGlosaMapper.class})
public interface GlosaMapper {

    GlosaMapper INSTANCE = Mappers.getMapper(GlosaMapper.class);

    RestGlosa convertToRest(Glosa glosa);

    Glosa convertToModel(RestGlosa restGlosa);
}
