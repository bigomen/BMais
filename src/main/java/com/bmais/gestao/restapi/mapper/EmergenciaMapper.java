package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Emergencia;
import com.bmais.gestao.restapi.restmodel.RestEmergencia;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface EmergenciaMapper {

    EmergenciaMapper INSTANCE = Mappers.getMapper(EmergenciaMapper.class);

    RestEmergencia convertToRest(Emergencia emergencia);

    Emergencia convertToModel(RestEmergencia restEmergencia);
}
