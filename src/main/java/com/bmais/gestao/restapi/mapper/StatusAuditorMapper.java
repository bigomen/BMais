package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusAuditor;
import com.bmais.gestao.restapi.restmodel.RestStatusAuditor;

@Mapper()
public interface StatusAuditorMapper {

    StatusAuditorMapper INSTANCE = Mappers.getMapper(StatusAuditorMapper.class);

    StatusAuditor convertToModel(RestStatusAuditor restStatusAuditor);

    RestStatusAuditor convertToRest(StatusAuditor statusAuditor);
}
