package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, ProntuarioMotivoAltaMapper.class, EnderecoMapper.class, StatusProntuarioVisitaHomeCareMapper.class, PacienteMapper.class, UsuarioMapper.class})
public interface ProntuarioVisitaHomeCareMapper {

    ProntuarioVisitaHomeCareMapper INSTANCE = Mappers.getMapper(ProntuarioVisitaHomeCareMapper.class);

    @Mappings(value = {
//    		@Mapping(target = "hospital", qualifiedByName = "toHospitalSimples"),
    		@Mapping(target = "usuario", source = "usuario.email"),
    		@Mapping(target = "numero", source = "id"),
    		@Mapping(target = "paciente", qualifiedByName = "toRestPaciente")
    })
    RestProntuarioVisitaHomeCare convertToRest(ProntuarioVisitaHomeCare prontuarioVisitaHomeCare);
    
    @Mappings(value = {
    		@Mapping(target = "motivoAlta", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toProntuarioMotivoAlta"),
    		@Mapping(target = "paciente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toPaciente"),
//    		@Mapping(target = "hospital", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toHospital"),
    		@Mapping(target = "usuario", ignore = true)
    })
    ProntuarioVisitaHomeCare convertToModel(RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCare);
    
    @Named(value = "toProntuario")
    default ProntuarioVisitaHomeCare toProntuario(RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCare)
    {
    	if(restProntuarioVisitaHomeCare == null || StringUtils.isBlank(restProntuarioVisitaHomeCare.getId()))
    	{
    		return null;
    	}
    	
    	return new ProntuarioVisitaHomeCare(UtilSecurity.decryptId(restProntuarioVisitaHomeCare.getId()));
    }
}
