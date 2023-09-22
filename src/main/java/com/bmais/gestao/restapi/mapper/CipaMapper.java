package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Cipa;
import com.bmais.gestao.restapi.restmodel.RestCipa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface CipaMapper {

    CipaMapper INSTANCE = Mappers.getMapper(CipaMapper.class);

    RestCipa convertToRest(Cipa cipa);

    Cipa convertToModel(RestCipa restCipa);
}
