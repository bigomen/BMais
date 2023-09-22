package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Ferias;
import com.bmais.gestao.restapi.restmodel.RestFerias;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, StatusFeriasMapper.class})
public interface FeriasMapper {

    FeriasMapper INSTANCE = Mappers.getMapper(FeriasMapper.class);

    RestFerias convertToRest(Ferias ferias);

    @Mapping(target = "colaborador", ignore = true)
    Ferias convertToModel(RestFerias restFerias);
}
