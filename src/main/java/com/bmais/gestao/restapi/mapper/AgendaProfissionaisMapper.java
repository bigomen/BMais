//package com.bmais.gestao.restapi.mapper;
//
//import com.bmais.gestao.restapi.model.AgendaProfissionais;
//import com.bmais.gestao.restapi.restmodel.RestAgendaProfissionais;
//import com.bmais.gestao.restapi.utility.UtilSecurity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.NullValueCheckStrategy;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(uses = {UtilSecurity.class, AuditorMapper.class, ClienteMapper.class, ServicoMapper.class, HospitalMapper.class, ColaboradorMapper.class})
//public interface AgendaProfissionaisMapper {
//
//    AgendaProfissionaisMapper INSTANCE = Mappers.getMapper(AgendaProfissionaisMapper.class);
//
////    @Mappings(value = {
////    		@Mapping(target = "cliente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "convertToSimplesCliente"),
////    		@Mapping(target = "servico", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toRestServico"),
////    		@Mapping(target = "hospital", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toHospitalSimples"),
////    })
////    RestAgendaProfissionais convertToRest(AgendaProfissionais agendaProfissionais);
//
//    @Mappings(value = {
//    		@Mapping(target = "medico", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toAuditor"),
//    		@Mapping(target = "enfermeiro", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toAuditor"),
//    		@Mapping(target = "cliente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toCliente"),
//    		@Mapping(target = "servico", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toServico"),
//    		@Mapping(target = "hospital", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toHospital"),
//    		@Mapping(target = "colaborador", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toColaborador")
//    })
//    AgendaProfissionais convertToModel(RestAgendaProfissionais restAgendaProfissionais);
//}
