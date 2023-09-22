package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusInternacao;
import com.bmais.gestao.restapi.restmodel.RestStatusInternacao;

@Mapper()
public interface StatusInternacaoMapper {

    StatusInternacaoMapper INSTANCE = Mappers.getMapper(StatusInternacaoMapper.class);

    RestStatusInternacao convertToRest(StatusInternacao statusInternacao);

    StatusInternacao convertToModel(RestStatusInternacao restStatusInternacao);
}
