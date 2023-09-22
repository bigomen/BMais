package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ConclusaoAuditoriaHC;
import com.bmais.gestao.restapi.restmodel.RestConclusaoAuditoriaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ConclusaoAuditoriaHCMapper {

    ConclusaoAuditoriaHCMapper INSTANCE = Mappers.getMapper(ConclusaoAuditoriaHCMapper.class);

    RestConclusaoAuditoriaHC convertToRest(ConclusaoAuditoriaHC conclusaoAuditoriaHC);

    @Mapping(target = "auditorias", ignore = true)
    ConclusaoAuditoriaHC convertToModel(RestConclusaoAuditoriaHC restConclusaoAuditoriaHC);
}
