package com.bmais.gestao.restapi.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
import com.bmais.gestao.restapi.model.projections.CapeantePSAmbulatorioProjection;
import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, StatusCapeanteMapper.class, UsuarioMapper.class, HospitalMapper.class, AuditorMapper.class, ResumoCapeantePSAmbulatorioMapper.class, ClienteMapper.class},
collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface CapeantePSAmbulatorioMapper {

    CapeantePSAmbulatorioMapper INSTANCE = Mappers.getMapper(CapeantePSAmbulatorioMapper.class);

    @Mappings(value = {
    		@Mapping(target = "numeroCapeante", source = "id"),
    		@Mapping(target = "hospital", qualifiedByName = "toHospitalSimples"),
    		@Mapping(target = "cliente", qualifiedByName = "convertToSimplesCliente"),
    		@Mapping(target = "auditorMedico", qualifiedByName = "toAuditorSimples"),
    		@Mapping(target = "auditorEnfermeiro", qualifiedByName = "toAuditorSimples")
    		
    })
    RestCapeantePSAmbulatorio convertToRest(CapeantePSAmbulatorio capeantePSAmbulatorio);
    
    @Mappings(value = {
    		@Mapping(target = "id", source = "idCapeante"),
    		@Mapping(target = "numeroCapeante", source = "idCapeante"),
    		@Mapping(target = "hospital.razaoSocial", source = "hospital"),
    		@Mapping(target = "cliente.razaoSocial", source = "cliente"),
    		@Mapping(target = "loteProtocolo", source = "protocolo"),
    		@Mapping(target = "status.descricao", source = "status"),
    		@Mapping(target = "dataFechamento", source = "dataFechamento"),
    		@Mapping(target = "valorCobrado", source = "valorCobrado"),
    		@Mapping(target = "valorGlosado", source = "valorGlosado"),
    		@Mapping(target = "valorLibarado", source = "valorLibarado")
    })
    RestCapeantePSAmbulatorio projectionToRest(CapeantePSAmbulatorioProjection projection);

    @Mappings(value = {
    		@Mapping(target = "hospital", qualifiedByName = "toHospital"),
    		@Mapping(target = "cliente", qualifiedByName = "toCliente"),
    		@Mapping(target = "auditorMedico", qualifiedByName = "toAuditor"),
    		@Mapping(target = "auditorEnfermeiro", qualifiedByName = "toAuditor")
    })
    CapeantePSAmbulatorio convertToModel(RestCapeantePSAmbulatorio restCapeantePSAmbulatorio);
}