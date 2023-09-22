package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.restmodel.RestAgenda;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, ColaboradorMapper.class, TipoAgendaMapper.class, UsuarioMapper.class, VinculoAuditorMapper.class})
public interface AgendaMapper {

    AgendaMapper INSTANCE = Mappers.getMapper(AgendaMapper.class);

    @Mappings(value = {
    		@Mapping(target = "usuarioInclusao", source = "usuarioInclusao.email"),
    		@Mapping(target = "usuarioEdicao", source = "usuarioEdicao.email"),
    		@Mapping(target = "colaborador", qualifiedByName = "toColaboradorSimples"),
    		@Mapping(target = "medico", qualifiedByName = "toRestAgendaVinculo"),
    		@Mapping(target = "enfermeiro", qualifiedByName = "toRestAgendaVinculo"),
    })
    RestAgenda convertToRest(Agenda agenda);

    @Mappings(value = {
    		@Mapping(target = "usuarioInclusao", ignore = true),
    		@Mapping(target = "usuarioEdicao", ignore = true),
    		@Mapping(target = "dataInclusao", ignore = true),
    		@Mapping(target = "dataEdicao", ignore = true),
    		@Mapping(target = "diaSemana", ignore = true),
    		@Mapping(target = "tipo",  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toTipoAgenda"),
    		@Mapping(target = "medico", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toVinculo"),
    		@Mapping(target = "enfermeiro", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toVinculo"),
    		@Mapping(target = "colaborador", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toColaborador")
    })
    Agenda convertToModel(RestAgenda restAgenda);
}
