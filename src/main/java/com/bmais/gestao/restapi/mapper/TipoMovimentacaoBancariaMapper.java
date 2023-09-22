package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoMovimentacaoBancaria;
import com.bmais.gestao.restapi.restmodel.RestTipoMovimentacaoBancaria;

@Mapper()
public interface TipoMovimentacaoBancariaMapper {

    TipoMovimentacaoBancariaMapper INSTANCE = Mappers.getMapper(TipoMovimentacaoBancariaMapper.class);

    RestTipoMovimentacaoBancaria convertToRest(TipoMovimentacaoBancaria tipoMovimentacaoBancaria);

    default TipoMovimentacaoBancaria convertToModel(RestTipoMovimentacaoBancaria restTipoMovimentacaoBancaria)
    {
    	if(restTipoMovimentacaoBancaria == null || StringUtils.isBlank(restTipoMovimentacaoBancaria.getId()))
    	{
    		return null;
    	}
    	
    	return new TipoMovimentacaoBancaria(Long.parseLong(restTipoMovimentacaoBancaria.getId()));
    }
}
