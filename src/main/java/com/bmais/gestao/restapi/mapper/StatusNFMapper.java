package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusNF;
import com.bmais.gestao.restapi.restmodel.RestStatusNF;

@Mapper()
public interface StatusNFMapper {

    StatusNFMapper INSTANCE = Mappers.getMapper(StatusNFMapper.class);

    RestStatusNF modelParaRest(StatusNF statusNF);

    StatusNF restParaModel(RestStatusNF restStatusNF);
}
