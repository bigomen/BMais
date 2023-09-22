package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusHospital;
import com.bmais.gestao.restapi.restmodel.RestStatusHospital;


@Mapper()
public interface StatusHospitalMapper {

    StatusHospitalMapper INSTANCE = Mappers.getMapper(StatusHospitalMapper.class);

    RestStatusHospital convertToRest(StatusHospital statusHospital);

    StatusHospital convertToModel(RestStatusHospital statusHospital);
}
