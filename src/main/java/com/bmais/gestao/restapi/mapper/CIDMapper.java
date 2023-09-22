package com.bmais.gestao.restapi.mapper;


import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.restmodel.RestCID;

@Mapper(uses = {StatusCIDMapper.class})
public interface CIDMapper {

    CIDMapper INSTANCE = Mappers.getMapper(CIDMapper.class);

    RestCID convertToRest(CID cid);

    CID convertToModel(RestCID restCID);
    
    @Named(value = "toCID")
    default CID convertToSimpleModel(RestCID restCID)
    {
    	if(restCID == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restCID.getId()))
    	{
    		return null;
    	}
    	
    	return new CID(Long.parseLong(restCID.getId()));
    }
}
