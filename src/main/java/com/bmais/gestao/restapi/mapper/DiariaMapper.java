package com.bmais.gestao.restapi.mapper;


import com.bmais.gestao.restapi.model.Diaria;
import com.bmais.gestao.restapi.restmodel.RestDiaria;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, AcomodacaoMapper.class})
public interface DiariaMapper{

    DiariaMapper INSTANCE = Mappers.getMapper(DiariaMapper.class);

    RestDiaria convertToRest(Diaria diaria);

    Diaria convertToModel(RestDiaria restDiaria);
}
