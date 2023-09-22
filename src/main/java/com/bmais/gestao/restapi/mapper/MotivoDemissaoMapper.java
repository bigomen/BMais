package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.MotivoDemissao;
import com.bmais.gestao.restapi.restmodel.RestMotivoDemissao;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface MotivoDemissaoMapper {

    MotivoDemissaoMapper INSTANCE = Mappers.getMapper(MotivoDemissaoMapper.class);

    RestMotivoDemissao convertToRest(MotivoDemissao motivoDemissao);

    MotivoDemissao convertToModel(RestMotivoDemissao restMotivoDemissao);
}
