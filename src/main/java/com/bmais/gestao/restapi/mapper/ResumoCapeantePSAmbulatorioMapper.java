package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ResumoCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.restmodel.RestResumoCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ResumoCapeantePSAmbulatorioMapper {

    ResumoCapeantePSAmbulatorioMapper INSTANCE = Mappers.getMapper(ResumoCapeantePSAmbulatorioMapper.class);

    RestResumoCapeantePSAmbulatorio convertToRest(ResumoCapeantePSAmbulatorio resumoCapeantePSAmbulatorio);

    @Mapping(target = "capeantePSAmbulatorio", ignore = true)
    ResumoCapeantePSAmbulatorio convertToModel(RestResumoCapeantePSAmbulatorio restResumoCapeantePSAmbulatorio);
}
