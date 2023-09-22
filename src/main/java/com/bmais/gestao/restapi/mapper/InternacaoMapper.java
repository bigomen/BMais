package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Internacao;
import com.bmais.gestao.restapi.restmodel.RestInternacao;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, PacienteMapper.class, HospitalMapper.class, StatusInternacaoMapper.class})
public interface InternacaoMapper {

    InternacaoMapper INSTANCE = Mappers.getMapper(InternacaoMapper.class);

    @Mappings(value = {
    		@Mapping(target = "numeroInternacao", source = "id"),
    		@Mapping(target = "hospital", qualifiedByName = "toHospitalSimples"),
    		@Mapping(target = "paciente", qualifiedByName = "toRestPaciente")
    		
    })
    RestInternacao convertToRest(Internacao internacao);
    
    @Named(value = "toSimplesInternacao")
    @Mappings(value = {
    		@Mapping(target = "numeroInternacao", ignore = true),
    		@Mapping(target = "hospital", ignore = true),
    		@Mapping(target = "paciente", ignore = true),
    		@Mapping(target = "status", ignore = true),
    		@Mapping(target = "dataHoraAlta", ignore = true),
    		@Mapping(target = "obito", ignore = true)    		
    })
    RestInternacao convertToSimpleRest(Internacao internacao);

    @Mappings(value = {
    		@Mapping(target = "paciente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toPaciente"),
    		@Mapping(target = "hospital", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toHospital"),
    		@Mapping(target = "status", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    })
    Internacao convertToModel(RestInternacao restInternacao);
    
    @Named(value = "toInternacao")
    default Internacao toInternacao(RestInternacao internacao)
    {
    	if(internacao == null || StringUtils.isBlank(internacao.getId()))
    	{
    		return null;
    	}
    	
    	return new Internacao(UtilSecurity.decryptId(internacao.getId()));
    }
}
