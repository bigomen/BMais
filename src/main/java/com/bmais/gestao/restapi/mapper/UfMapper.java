package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.UF;
import com.bmais.gestao.restapi.restmodel.RestUf;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {UtilSecurity.class})
public interface UfMapper {

    UfMapper INSTANCE = Mappers.getMapper(UfMapper.class);

    RestUf convertToRest(UF uf);

    UF convertToModel(RestUf restUf);
}
