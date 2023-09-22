package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Cargo;
import com.bmais.gestao.restapi.restmodel.RestCargo;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    RestCargo convertToRest(Cargo cargo);

    Cargo convertToModel(RestCargo restCargo);
}
