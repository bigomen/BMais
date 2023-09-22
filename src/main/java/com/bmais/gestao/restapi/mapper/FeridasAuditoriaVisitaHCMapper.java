package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.FeridasAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestFeridasAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface FeridasAuditoriaVisitaHCMapper {

    FeridasAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(FeridasAuditoriaVisitaHCMapper.class);

    RestFeridasAuditoriaVisitaHC convertToRest(FeridasAuditoriaVisitaHC feridasAuditoriaVisitaHC);

    @Mapping(target = "visita",ignore = true)
    FeridasAuditoriaVisitaHC convertToModel(RestFeridasAuditoriaVisitaHC restFeridasAuditoriaVisitaHC);
}
