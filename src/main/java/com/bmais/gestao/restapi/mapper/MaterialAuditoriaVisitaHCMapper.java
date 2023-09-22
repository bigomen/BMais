package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.MaterialAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestMaterialAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface MaterialAuditoriaVisitaHCMapper {

    MaterialAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(MaterialAuditoriaVisitaHCMapper.class);

    RestMaterialAuditoriaVisitaHC convertToRest(MaterialAuditoriaVisitaHC materialAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    MaterialAuditoriaVisitaHC convertToModel(RestMaterialAuditoriaVisitaHC restMaterialAuditoriaVisitaHC);
}
