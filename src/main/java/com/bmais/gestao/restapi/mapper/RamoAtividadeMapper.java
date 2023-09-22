package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.RamoAtividade;
import com.bmais.gestao.restapi.restmodel.RestRamoAtividade;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {UtilSecurity.class})
public interface RamoAtividadeMapper {

    RamoAtividadeMapper INSTANCE = Mappers.getMapper(RamoAtividadeMapper.class);

    RestRamoAtividade convertToRest(RamoAtividade ramoAtividade);

    RamoAtividade convertToModel(RestRamoAtividade restRamoAtividade);
}
