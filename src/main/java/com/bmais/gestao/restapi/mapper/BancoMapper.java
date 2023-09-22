package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Banco;
import com.bmais.gestao.restapi.restmodel.RestBanco;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface BancoMapper {

    BancoMapper INSTANCE = Mappers.getMapper(BancoMapper.class);

    RestBanco convertToRest(Banco banco);

    Banco convertToModel(RestBanco restBanco);
}
