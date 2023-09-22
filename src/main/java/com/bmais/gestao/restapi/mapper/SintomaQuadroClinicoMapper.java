package com.bmais.gestao.restapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.SintomaQuadroClinico;
import com.bmais.gestao.restapi.restmodel.RestSintomaQuadroClinico;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class})
public interface SintomaQuadroClinicoMapper {

    SintomaQuadroClinicoMapper INSTANCE = Mappers.getMapper(SintomaQuadroClinicoMapper.class);

    RestSintomaQuadroClinico convertToRest(SintomaQuadroClinico sintomaQuadroClinico);

    @Mappings(value = {
    		@Mapping(target = "auditorias", ignore = true)
    })
    SintomaQuadroClinico convertToModel(RestSintomaQuadroClinico restSintomaQuadroClinico);
}
