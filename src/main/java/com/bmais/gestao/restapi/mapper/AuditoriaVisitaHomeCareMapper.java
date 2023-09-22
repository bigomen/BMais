package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.AuditoriaVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestAuditoriaVisitaHomeCare;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, SintomaQuadroClinicoMapper.class, DiagnosticoAuditoriaVisitaHCMapper.class, FeridasAuditoriaVisitaHCMapper.class, 
		StomiaAuditoriaVisitaHCMapper.class, ServicoPrestadoAuditoriaVisitaHCMapper.class, MaterialAuditoriaVisitaHCMapper.class, AuditorMapper.class,
		MedicamentoAuditoriaVisitaHCMapper.class, EquipamentoAuditoriaVisitaHCMapper.class, AvaliacaoDependenciaVisitaHCMapper.class, ConclusaoAuditoriaHCMapper.class},
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AuditoriaVisitaHomeCareMapper {

    AuditoriaVisitaHomeCareMapper INSTANCE = Mappers.getMapper(AuditoriaVisitaHomeCareMapper.class);

    @Mappings(value = {
    		@Mapping(target = "auditor", qualifiedByName = "toAuditorSimples")
    })
    RestAuditoriaVisitaHomeCare convertToRest(AuditoriaVisitaHomeCare auditoriaVisitaHomeCare);

    @Mappings(value = {
    		@Mapping(target = "auditor", qualifiedByName = "toAuditor")
    })
    AuditoriaVisitaHomeCare convertToModel(RestAuditoriaVisitaHomeCare restAuditoriaVisitaHomeCare);
}
