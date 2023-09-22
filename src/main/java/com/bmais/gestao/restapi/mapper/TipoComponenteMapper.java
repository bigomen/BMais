package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoComponente;
import com.bmais.gestao.restapi.restmodel.RestTipoComponente;

@Mapper()
public interface TipoComponenteMapper {

    TipoComponenteMapper INSTANCE = Mappers.getMapper(TipoComponenteMapper.class);
    

    @Mappings(value = {
    		@Mapping(target = "no", source = "descricao" ,qualifiedByName = "toNo")
    })
    RestTipoComponente convertToRest(TipoComponente tipoComponente);

    TipoComponente convertToModel(RestTipoComponente restTipoComponente);
    
    
    @Named("toNo")
    default String fornataNoh(String descricao)
    {
    	return descricao
    			.replaceAll(" ", "")
    			.replaceAll("de", "")
    			.replaceAll("ó", "o")
    			.toLowerCase();
    }
}
