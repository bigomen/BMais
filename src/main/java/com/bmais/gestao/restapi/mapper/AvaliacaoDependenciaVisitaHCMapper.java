package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.AvaliacaoDependenciaVisitaHC;
import com.bmais.gestao.restapi.restmodel.RestAvaliacaoDependenciaVisitaHC;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface AvaliacaoDependenciaVisitaHCMapper {

    AvaliacaoDependenciaVisitaHCMapper INSTANCE = Mappers.getMapper(AvaliacaoDependenciaVisitaHCMapper.class);

    RestAvaliacaoDependenciaVisitaHC convertToRest(AvaliacaoDependenciaVisitaHC avaliacaoDependenciaVisitaHC);

    @Mappings({
            @Mapping(target = "visita", ignore = true)
    })
    AvaliacaoDependenciaVisitaHC convertToModel(RestAvaliacaoDependenciaVisitaHC restAvaliacaoDependenciaVisitaHC);
}
