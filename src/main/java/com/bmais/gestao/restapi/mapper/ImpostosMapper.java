package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Impostos;
import com.bmais.gestao.restapi.restmodel.RestImpostos;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ImpostosMapper {

    ImpostosMapper INSTANCE = Mappers.getMapper(ImpostosMapper.class);

    RestImpostos modelParaRest(Impostos impostos);

    Impostos restParaModel(RestImpostos restImpostos);
}
