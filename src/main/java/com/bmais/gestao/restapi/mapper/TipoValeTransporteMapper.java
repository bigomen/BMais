package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoValeTransporte;
import com.bmais.gestao.restapi.restmodel.RestTipoValeTransporte;

@Mapper()
public interface TipoValeTransporteMapper {

    TipoValeTransporteMapper INSTANCE = Mappers.getMapper(TipoValeTransporteMapper.class);

    RestTipoValeTransporte convertToRest(TipoValeTransporte tipoValeTransporte);

    TipoValeTransporte convertToModel(RestTipoValeTransporte restTipoValeTransporte);
}
