package com.bmais.gestao.restapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomeCare;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, ProntuarioVisitaHomeCareMapper.class, AuditoriaVisitaHomeCareMapper.class, CIDMapper.class, SintomaQuadroClinicoMapper.class,
		StatusVisitaMapper.class, UsuarioMapper.class})
public interface VisitaHomeCareMapper {

    VisitaHomeCareMapper INSTANCE = Mappers.getMapper(VisitaHomeCareMapper.class);

    @Mappings(value = {
    		@Mapping(target = "usuario", source = "usuario.nome")
    })
    RestVisitaHomeCare convertToRest(VisitaHomeCare visitaHomeCare);

    @Mappings(value = {
//    		@Mapping(target = "prontuario", qualifiedByName = "toProntuario"),
    		@Mapping(target = "usuario", ignore = true)
    })
    VisitaHomeCare convertToModel(RestVisitaHomeCare restVisitaHomeCare);
}
