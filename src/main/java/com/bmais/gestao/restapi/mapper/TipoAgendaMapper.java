package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoAgenda;
import com.bmais.gestao.restapi.restmodel.RestTipoAgenda;

@Mapper()
public interface TipoAgendaMapper {

    TipoAgendaMapper INSTANCE = Mappers.getMapper(TipoAgendaMapper.class);

    RestTipoAgenda convertToRest(TipoAgenda tipoAgenda);

    TipoAgenda convertToModel(RestTipoAgenda restTipoAgenda);
    
    @Named(value = "toTipoAgenda")
    default TipoAgenda toTipoAgenda(RestTipoAgenda restTipoAgenda)
    {
    	if(restTipoAgenda == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restTipoAgenda.getId()))
    	{
    		return null;
    	}
    	
    	return new TipoAgenda(Long.parseLong(restTipoAgenda.getId()));
    }
}
