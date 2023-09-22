package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Acomodacao;
import com.bmais.gestao.restapi.restmodel.RestAcomodacao;

@Mapper()
public interface AcomodacaoMapper {

    AcomodacaoMapper INSTANCE = Mappers.getMapper(AcomodacaoMapper.class);

    RestAcomodacao convertToRest(Acomodacao acomodacao);

    @Named(value = "toAcomodacao")
    default Acomodacao convertToModel(RestAcomodacao restAcomodacao)
    {
    	if(restAcomodacao == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restAcomodacao.getId()))
    	{
    		return null;
    	}
    	
    	return new Acomodacao(Long.parseLong(restAcomodacao.getId()));
    }
}
