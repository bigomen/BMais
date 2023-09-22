package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ComponenteAltoCusto;
import com.bmais.gestao.restapi.restmodel.RestComponenteAltoCusto;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, TipoComponenteMapper.class})
public interface ComponenteAltoCustoMapper {

    ComponenteAltoCustoMapper INSTANCE = Mappers.getMapper(ComponenteAltoCustoMapper.class);

    RestComponenteAltoCusto convertToRest(ComponenteAltoCusto componenteAltoCusto);

    ComponenteAltoCusto convertToModel(RestComponenteAltoCusto restComponenteAltoCusto);
}
