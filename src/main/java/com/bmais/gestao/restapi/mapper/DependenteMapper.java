package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Dependente;
import com.bmais.gestao.restapi.restmodel.RestDependente;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface DependenteMapper {

    DependenteMapper INSTANCE = Mappers.getMapper(DependenteMapper.class);

    RestDependente convertToRest(Dependente dependente);

    @Mapping(target = "colaborador", ignore = true)
    Dependente convertToModel(RestDependente restDependente);
}
