package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.EquipamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestEquipamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface EquipamentoAuditoriaVisitaHCMapper {

    EquipamentoAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(EquipamentoAuditoriaVisitaHCMapper.class);

    RestEquipamentoAuditoriaVisitaHC convertToRest(EquipamentoAuditoriaVisitaHC equipamentoAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    EquipamentoAuditoriaVisitaHC convertToModel(RestEquipamentoAuditoriaVisitaHC restEquipamentoAuditoriaVisitaHC);
}
