package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.PlanoContas;
import com.bmais.gestao.restapi.restmodel.RestPlanoContas;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class})
public interface PlanoContasMapper
{
	PlanoContasMapper INSTANCE = Mappers.getMapper(PlanoContasMapper.class);
	
	default PlanoContas convertToModel(RestPlanoContas restPlanoContas)
	{
		if(restPlanoContas == null || StringUtils.isBlank(restPlanoContas.getId()))
		{
			return null;
		}
		
		return new PlanoContas(UtilSecurity.decryptId(restPlanoContas.getId()));
	}
	
	RestPlanoContas convertToRest (PlanoContas planoContas);
}
