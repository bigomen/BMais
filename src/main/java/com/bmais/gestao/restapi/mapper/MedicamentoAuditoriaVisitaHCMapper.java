package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.MedicamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestMedicamentoAuditoriaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface MedicamentoAuditoriaVisitaHCMapper {

    MedicamentoAuditoriaVisitaHCMapper INSTANCE = Mappers.getMapper(MedicamentoAuditoriaVisitaHCMapper.class);

    RestMedicamentoAuditoriaVisitaHC convertToRest(MedicamentoAuditoriaVisitaHC medicamentoAuditoriaVisitaHC);

    @Mapping(target = "visita", ignore = true)
    MedicamentoAuditoriaVisitaHC convertToModel(RestMedicamentoAuditoriaVisitaHC restMedicamentoAuditoriaVisitaHC);
}
