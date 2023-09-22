package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.EvolucaoColaborador;
import com.bmais.gestao.restapi.restmodel.RestEvolucaoColaborador;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface EvolucaoColaboradorMapper {

    EvolucaoColaboradorMapper INSTANCE = Mappers.getMapper(EvolucaoColaboradorMapper.class);

    RestEvolucaoColaborador convertToRest(EvolucaoColaborador evolucaoColaborador);

    EvolucaoColaborador convertToModel(RestEvolucaoColaborador restEvolucaoColaborador);
}
