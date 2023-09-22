package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusServico;
import com.bmais.gestao.restapi.restmodel.RestStatusServico;

@Mapper()
public interface StatusServicoMapper {

    StatusServicoMapper INSTANCE = Mappers.getMapper(StatusServicoMapper.class);

    RestStatusServico convertToRest(StatusServico statusServico);

    StatusServico convertToModel(RestStatusServico restStatusServico);
}
