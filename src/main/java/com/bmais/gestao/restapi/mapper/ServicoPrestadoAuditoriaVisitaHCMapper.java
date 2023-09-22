package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ServicoPrestadoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestServicoPrestadoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ServicoPrestadoAuditoriaVisitaHCMapper {

    ServicoPrestadoAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(ServicoPrestadoAuditoriaVisitaHCMapper.class);

    RestServicoPrestadoAuditoriaVisitaHC convertToRest(ServicoPrestadoAuditoriaVisitaHC servicoPrestadoAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    ServicoPrestadoAuditoriaVisitaHC convertToModel(RestServicoPrestadoAuditoriaVisitaHC restServicoPrestadoAuditoriaVisitaHC);
}
