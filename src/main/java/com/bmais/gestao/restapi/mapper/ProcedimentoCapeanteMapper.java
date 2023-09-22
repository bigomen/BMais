package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ProcedimentoCapeante;
import com.bmais.gestao.restapi.restmodel.RestProcedimentoCapeante;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, TussMapper.class})
public interface ProcedimentoCapeanteMapper {

    ProcedimentoCapeanteMapper INSTANCE = Mappers.getMapper(ProcedimentoCapeanteMapper.class);

    RestProcedimentoCapeante convertToRest(ProcedimentoCapeante procedimentoCapeante);

    @Mappings(value = {
    		@Mapping(target = "tuss", qualifiedByName = "toTuss")
    })
    ProcedimentoCapeante convertToModel(RestProcedimentoCapeante restProcedimentoCapeante);
}
