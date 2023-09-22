package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Beneficio;
import com.bmais.gestao.restapi.restmodel.RestBeneficio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface BeneficioMapper {

    BeneficioMapper INSTANCE = Mappers.getMapper(BeneficioMapper.class);

    RestBeneficio convertToRest(Beneficio beneficio);

    Beneficio convertToModel(RestBeneficio restBeneficio);
}
