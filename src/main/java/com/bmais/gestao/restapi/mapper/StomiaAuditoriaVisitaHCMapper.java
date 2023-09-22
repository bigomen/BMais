package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.StomiaAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestStomiaAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface StomiaAuditoriaVisitaHCMapper {

    StomiaAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(StomiaAuditoriaVisitaHCMapper.class);

    RestStomiaAuditoriaVisitaHC convertToRest(StomiaAuditoriaVisitaHC stomiaAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    StomiaAuditoriaVisitaHC convertToModel(RestStomiaAuditoriaVisitaHC restStomiaAuditoriaVisitaHC);
}
