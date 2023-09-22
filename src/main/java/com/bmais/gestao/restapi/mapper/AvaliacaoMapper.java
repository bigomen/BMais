package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Avaliacao;
import com.bmais.gestao.restapi.restmodel.RestAvaliacao;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface AvaliacaoMapper {

    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);

    RestAvaliacao convertToRest(Avaliacao avaliacao);

    Avaliacao convertToModel(RestAvaliacao restAvaliacao);
}
