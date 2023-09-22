package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ProcedimentoCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.restmodel.RestProcedimentoCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ProcedimentoCapeantePSAmbulatorioMapper {

    ProcedimentoCapeantePSAmbulatorioMapper INSTANCE = Mappers.getMapper(ProcedimentoCapeantePSAmbulatorioMapper.class);

    RestProcedimentoCapeantePSAmbulatorio convertToRest(ProcedimentoCapeantePSAmbulatorio procedimentoCapeantePSAmbulatorio);

    @Mapping(target = "capeantePSAmbulatorio", ignore = true)
    ProcedimentoCapeantePSAmbulatorio convertToModel(RestProcedimentoCapeantePSAmbulatorio restProcedimentoCapeantePSAmbulatorio);
}
