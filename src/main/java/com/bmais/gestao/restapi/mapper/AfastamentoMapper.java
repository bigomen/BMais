package com.bmais.gestao.restapi.mapper;


import com.bmais.gestao.restapi.model.Afastamento;
import com.bmais.gestao.restapi.restmodel.RestAfastamento;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, TipoAfastamentoMapper.class, CIDMapper.class})
public interface AfastamentoMapper {

    AfastamentoMapper INSTANCE = Mappers.getMapper(AfastamentoMapper.class);

    RestAfastamento convertToRest(Afastamento afastamento);

    @Mapping(target = "colaborador", ignore = true)
    Afastamento convertToModel(RestAfastamento restAfastamento);
}
