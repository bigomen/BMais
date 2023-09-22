package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusPaciente;
import com.bmais.gestao.restapi.restmodel.RestStatusPaciente;

@Mapper()
public interface StatusPacienteMapper {

    StatusPacienteMapper INSTANCE = Mappers.getMapper(StatusPacienteMapper.class);

    RestStatusPaciente convertToRest(StatusPaciente statusPaciente);

    StatusPaciente convertToModel(RestStatusPaciente restStatusPaciente);
}
