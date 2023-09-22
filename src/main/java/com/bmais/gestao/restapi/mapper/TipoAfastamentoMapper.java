package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoAfastamento;
import com.bmais.gestao.restapi.restmodel.RestTipoAfastamento;

@Mapper()
public interface TipoAfastamentoMapper {

    TipoAfastamentoMapper INSTANCE = Mappers.getMapper(TipoAfastamentoMapper.class);

    RestTipoAfastamento convertToRest(TipoAfastamento tipoAfastamento);

    TipoAfastamento convertToModel(RestTipoAfastamento restTipoAfastamento);
}
