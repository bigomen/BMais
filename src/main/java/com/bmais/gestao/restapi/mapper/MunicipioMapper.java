package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.restmodel.RestMunicipio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class}, componentModel = "spring")
public interface MunicipioMapper {

    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    RestMunicipio convertToRest(Municipio municipio);

    Municipio convertToModel(RestMunicipio restMunicipio);
}
