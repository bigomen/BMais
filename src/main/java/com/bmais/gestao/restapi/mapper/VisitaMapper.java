package com.bmais.gestao.restapi.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Visita;
import com.bmais.gestao.restapi.restmodel.RestVisita;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, CurativoMapper.class, ComponenteAltoCustoMapper.class, AvaliacaoRelatorioMapper.class, 
		AcomodacaoMapper.class, InternacaoMapper.class, TussMapper.class, AuditorMapper.class, CIDMapper.class, StatusVisitaMapper.class, AcomodacaoVisitaMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface VisitaMapper {

    VisitaMapper INSTANCE = Mappers.getMapper(VisitaMapper.class);

    @Mappings(value = {
            @Mapping(target = "avaliacaoRelatorio", qualifiedByName = "toRestAvaliacaoVisitaConcorrente"),
            @Mapping(target = "auditor", qualifiedByName = "toAuditorVisitaSimples")
    })
    RestVisita convertToRest(Visita visita);

    @Mappings(value = {
            @Mapping(target = "auditor", qualifiedByName = "toAuditorVisita"),
            @Mapping(target = "glosaDiariaAcomodacao", qualifiedByName = "toAcomodacao"),
            @Mapping(target = "trocaDiariaDe", qualifiedByName = "toAcomodacao"),
            @Mapping(target = "trocaDiariaPara", qualifiedByName = "toAcomodacao"),
            @Mapping(target = "cidPrincipal", qualifiedByName = "toCID"),
            @Mapping(target = "cidSecundario", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toCID"),
            @Mapping(target = "procedimentoNegado", qualifiedByName = "toTuss", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(target = "trocaProcedimentoDe", qualifiedByName = "toTuss", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(target = "trocaProcedimentoPara", qualifiedByName = "toTuss", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    })
    Visita convertToModel(RestVisita restVisita);
}
