package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ColaboradorBeneficio;
import com.bmais.gestao.restapi.restmodel.RestColaboradorBeneficio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, BeneficioMapper.class})
public interface ColaboradorBeneficioMapper {

    ColaboradorBeneficioMapper INSTANCE = Mappers.getMapper(ColaboradorBeneficioMapper.class);

    RestColaboradorBeneficio convertToRest(ColaboradorBeneficio colaboradorBeneficio);

    @Mappings(value = {
            @Mapping(target = "colaborador", ignore = true),
            @Mapping(target = "dependente", ignore = true)
    })
    ColaboradorBeneficio convertToModel(RestColaboradorBeneficio restColaboradorBeneficio);
}
