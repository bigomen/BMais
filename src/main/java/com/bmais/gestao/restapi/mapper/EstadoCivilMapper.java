package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.EstadoCivil;
import com.bmais.gestao.restapi.restmodel.RestEstadoCivil;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface EstadoCivilMapper {

    EstadoCivilMapper INSTANCE = Mappers.getMapper(EstadoCivilMapper.class);

    RestEstadoCivil convertToRest(EstadoCivil estadoCivil);

    EstadoCivil convertToModel(RestEstadoCivil restEstadoCivil);
}
