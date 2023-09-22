package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.StatusProdutividade;
import com.bmais.gestao.restapi.restmodel.RestStatusProdutividade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusProdutividadeMapper {

    StatusProdutividadeMapper INSTANCE = Mappers.getMapper(StatusProdutividadeMapper.class);

    RestStatusProdutividade convertToRest(StatusProdutividade statusProdutividade);

    StatusProdutividade convertToModel(RestStatusProdutividade restStatusProdutividade);
}
