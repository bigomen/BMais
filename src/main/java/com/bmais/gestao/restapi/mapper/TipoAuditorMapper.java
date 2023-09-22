package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoAuditor;
import com.bmais.gestao.restapi.restmodel.RestTipoAuditor;

@Mapper()
public interface TipoAuditorMapper {

    TipoAuditorMapper INSTANCE = Mappers.getMapper(TipoAuditorMapper.class);

    RestTipoAuditor convertToRest(TipoAuditor tipoAuditor);

    TipoAuditor convertToModel(RestTipoAuditor restTipoAuditor);
}
