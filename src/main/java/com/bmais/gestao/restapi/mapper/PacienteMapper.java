package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.restmodel.RestPaciente;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, ClienteMapper.class, StatusPacienteMapper.class})
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    @Named(value = "toRestPaciente")
    @Mappings(value = {
            @Mapping(target = "cliente", qualifiedByName = "convertToSimplesCliente")
    })
    RestPaciente convertToRest(Paciente paciente);
    
    @Named(value = "toSimpleRestPaciente")
    @Mappings(value = {
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "relatorios", ignore = true)
    })
    RestPaciente convertToSimpleRest(Paciente paciente);

    @Mappings(value = {
            @Mapping(target = "cliente", qualifiedByName = "toCliente"),
    })
    Paciente convertToModel(RestPaciente restPaciente);
    
    @Named(value = "toPaciente")
    default Paciente toPaciente(RestPaciente restPaciente)
    {
    	if(restPaciente == null || StringUtils.isBlank(restPaciente.getId()))
    	{
    		return null;
    	}
    	return new Paciente(UtilSecurity.decryptId(restPaciente.getId()));
    }
}
