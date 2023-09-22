package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.DiagnosticoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestDiagnosticoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, CIDMapper.class})
public interface DiagnosticoAuditoriaVisitaHCMapper {

    DiagnosticoAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(DiagnosticoAuditoriaVisitaHCMapper.class);

    RestDiagnosticoAuditoriaVisitaHC convertToRest(DiagnosticoAuditoriaVisitaHC diagnosticoAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    DiagnosticoAuditoriaVisitaHC convertToModel(RestDiagnosticoAuditoriaVisitaHC restDiagnosticoAuditoriaVisitaHC);
}
