package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.StatusFilial;
import com.bmais.gestao.restapi.restmodel.RestStatusFilial;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface StatusFilialMapper {

    StatusFilialMapper INSTANCE = Mappers.getMapper(StatusFilialMapper.class);

    RestStatusFilial convertToRest(StatusFilial statusFilial);

    StatusFilial convertToModel(RestStatusFilial restStatusFilial);
}
