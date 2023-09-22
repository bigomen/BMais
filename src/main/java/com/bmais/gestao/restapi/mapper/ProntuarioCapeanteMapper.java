package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeante;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, PacienteMapper.class, UsuarioMapper.class, HospitalClienteMapper.class, StatusCapeanteMapper.class})
public interface ProntuarioCapeanteMapper {

    ProntuarioCapeanteMapper INSTANCE = Mappers.getMapper(ProntuarioCapeanteMapper.class);

    @Mappings(value = {
    		@Mapping(target = "numero", source = "id"),
    		@Mapping(target = "usuario", source = "usuario.email"),
    		@Mapping(target = "hospital", qualifiedByName = "toRestHospitalCliente"),
    		@Mapping(target = "paciente", qualifiedByName = "toRestPaciente")
    })
    RestProntuarioCapeante convertToRest(ProntuarioCapeante capeanteInternacao);

    @Mappings(value = {
    		@Mapping(target = "usuario", ignore = true),
    		@Mapping(target = "paciente", qualifiedByName = "toPaciente"),
    		@Mapping(target = "hospital", qualifiedByName = "toModelHospitalCliente")
    })
    ProntuarioCapeante convertToModel(RestProntuarioCapeante restCapeanteInternacao);
}
