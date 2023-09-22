package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.AcomodacaoVisita;
import com.bmais.gestao.restapi.restmodel.RestAcomodacaoVisita;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        UtilSecurity.class,
        AcomodacaoMapper.class
})
public interface AcomodacaoVisitaMapper {

    AcomodacaoVisitaMapper INSTANCE = Mappers.getMapper(AcomodacaoVisitaMapper.class);

    RestAcomodacaoVisita convertToRest(AcomodacaoVisita acomodacaoVisita);

    @Mappings({
            @Mapping(target = "acomodacao", qualifiedByName = "toAcomodacao"),
            @Mapping(target = "visita", ignore = true)
    })
    AcomodacaoVisita convertToModel(RestAcomodacaoVisita restAcomodacaoVisita);
}
