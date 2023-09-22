package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Tuss;
import com.bmais.gestao.restapi.restmodel.RestTuss;

@Mapper(uses = StatusTussMapper.class)
public interface TussMapper {

    TussMapper INSTANCE = Mappers.getMapper(TussMapper.class);

    RestTuss convertToRest(Tuss tuss);
    
    Tuss convertToModel (RestTuss restTuss);

    @Named(value = "toTuss")
    default Tuss convertToSimpleModel(RestTuss restTuss)
    {
    	if(restTuss == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restTuss.getId()))
    	{
    		return null;
    	}
    	
    	return new Tuss(Long.parseLong(restTuss.getId()));
    }
}
