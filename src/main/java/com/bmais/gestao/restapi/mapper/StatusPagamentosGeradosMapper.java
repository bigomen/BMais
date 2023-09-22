package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusPagamentosGerados;
import com.bmais.gestao.restapi.restmodel.RestStatusPagamentosGerados;

@Mapper()
public interface StatusPagamentosGeradosMapper {

    StatusPagamentosGeradosMapper INSTANCE = Mappers.getMapper(StatusPagamentosGeradosMapper.class);

    RestStatusPagamentosGerados convertToRest(StatusPagamentosGerados statusPagamentosGerados);

    StatusPagamentosGerados convertToModel(RestStatusPagamentosGerados restStatusPagamentosGerados);
}
