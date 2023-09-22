package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.JustificativaPermanencia;
import com.bmais.gestao.restapi.restmodel.RestJustificativaPermanencia;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface JustificativaPermanenciaMapper {

    JustificativaPermanenciaMapper INSTANCE = Mappers.getMapper(JustificativaPermanenciaMapper.class);

    RestJustificativaPermanencia convertToRest(JustificativaPermanencia justificativaPermanencia);

    JustificativaPermanencia convertToModel(RestJustificativaPermanencia restJustificativaPermanencia);
}
