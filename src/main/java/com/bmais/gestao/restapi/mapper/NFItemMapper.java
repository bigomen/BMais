package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.NFItem;
import com.bmais.gestao.restapi.restmodel.RestNFItem;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface NFItemMapper {

    NFItemMapper INSTANCE = Mappers.getMapper(NFItemMapper.class);

    RestNFItem modelParaRest(NFItem nfItem);

    NFItem restParaModel(RestNFItem restNFItem);

}
