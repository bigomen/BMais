package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusMovimentacaoBancaria;
import com.bmais.gestao.restapi.restmodel.RestStatusMovimentacaoBancaria;

@Mapper()
public interface StatusMovimentacaoBancariaMapper {

    StatusMovimentacaoBancariaMapper INSTANCE = Mappers.getMapper(StatusMovimentacaoBancariaMapper.class);

    RestStatusMovimentacaoBancaria convertToRest(StatusMovimentacaoBancaria statusMovimentacaoBancaria);

    default StatusMovimentacaoBancaria convertToModel(RestStatusMovimentacaoBancaria restStatusMovimentacaoBancaria)
    {
    	if(restStatusMovimentacaoBancaria == null || StringUtils.isBlank(restStatusMovimentacaoBancaria.getId()))
    	{
    		return null;
    	}
    	
    	return new StatusMovimentacaoBancaria(Long.parseLong(restStatusMovimentacaoBancaria.getId()));
    }
}
