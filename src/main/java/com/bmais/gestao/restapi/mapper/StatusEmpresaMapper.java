package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusEmpresa;
import com.bmais.gestao.restapi.restmodel.RestStatusEmpresa;

@Mapper()
public interface StatusEmpresaMapper {

    StatusEmpresaMapper INSTANCE = Mappers.getMapper(StatusEmpresaMapper.class);

    RestStatusEmpresa convertToRest(StatusEmpresa statusEmpresa);

    StatusEmpresa convertToModel(RestStatusEmpresa restStatusEmpresa);
}
