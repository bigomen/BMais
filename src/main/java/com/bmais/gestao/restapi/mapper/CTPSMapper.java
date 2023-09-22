package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.CTPS;
import com.bmais.gestao.restapi.restmodel.RestCTPS;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface CTPSMapper {

    CTPSMapper INSTANCE = Mappers.getMapper(CTPSMapper.class);

    RestCTPS convertToRest(CTPS ctps);

    CTPS convertToModel(RestCTPS restCTPS);
}
