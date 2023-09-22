package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.model.projections.EscalaProjection;
import com.bmais.gestao.restapi.restmodel.RestCobertura;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, AuditorMapper.class, VinculoAuditorMapper.class})
public interface CoberturaMapper {

    CoberturaMapper INSTANCE = Mappers.getMapper(CoberturaMapper.class);

    @Mappings(value = {
    		@Mapping(target = "vinculo", source = "escala", qualifiedByName = "toVinculoCobertura")
    })
    Cobertura convertToModel(RestCobertura restCobertura);
    
	@Mappings(value = {
			@Mapping(target = "id", source = "cobertura.id"),
			@Mapping(target = "observacao", source = "cobertura.observacao"),
			@Mapping(target = "periodoInicio", source = "cobertura.periodoInicio"),
			@Mapping(target = "periodoFim", source = "cobertura.periodoFim"),
			@Mapping(target = "auditor", qualifiedByName = "toRestAuditorEscala")
	})
	RestCobertura convertToRest(EscalaProjection escala);
	
	@Named(value = "toRestCoberturaEscala")
	@Mappings(value = {
			@Mapping(target = "auditor", source = "auditor", qualifiedByName = "toRestAuditorEscala")
	})
	RestCobertura convertToRestEscala(Cobertura cobertura);
}
