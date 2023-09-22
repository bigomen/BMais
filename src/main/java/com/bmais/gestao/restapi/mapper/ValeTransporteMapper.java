package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ValeTransporte;
import com.bmais.gestao.restapi.restmodel.RestValeTransporte;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, TipoValeTransporteMapper.class})
public interface ValeTransporteMapper {

    ValeTransporteMapper INSTANCE = Mappers.getMapper(ValeTransporteMapper.class);

    RestValeTransporte convertToRest(ValeTransporte valeTransporte);

    @Mapping(target = "colaborador", ignore = true)
    ValeTransporte convertToModel(RestValeTransporte restValeTransporte);
}
