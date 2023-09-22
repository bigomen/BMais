//package com.bmais.gestao.restapi.mapper;
//
//import com.bmais.gestao.restapi.model.Capeante;
//import com.bmais.gestao.restapi.model.CapeanteInternacao;
//import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
//import com.bmais.gestao.restapi.model.HistoricoCapeante;
//import com.bmais.gestao.restapi.restmodel.RestCapeante;
//import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
//import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.utility.UtilSecurity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(uses = {UtilSecurity.class, UsuarioMapper.class, CapeantePSAmbulatorioMapper.class})
//public interface HistoricoCapeanteMapper {
//
//    HistoricoCapeanteMapper INSTANCE = Mappers.getMapper(HistoricoCapeanteMapper.class);
//
//    @Mapping(target = "capeante", qualifiedByName = "toRestCapeante")
//    RestHistoricoCapeante convertToRest(HistoricoCapeante historicoCapeante);
//
//    @Named("toRestCapeante")
//    default RestCapeante convertToRestCapeante(Capeante capeante) {
//        if (capeante instanceof CapeantePSAmbulatorio) {
//            return CapeantePSAmbulatorioMapper.INSTANCE.convertToRest((CapeantePSAmbulatorio) capeante);
//        }
//        if(capeante instanceof CapeanteInternacao){
//            return CapeanteInternacaoMapper.INSTANCE.convertToRest((CapeanteInternacao) capeante);
//        }
//        return null;
//    }
//
//    @Mapping(target = "capeante", qualifiedByName = "toCapeante")
//    HistoricoCapeante convertToModel(RestHistoricoCapeante restHistoricoCapeante);
//
//    @Named("toCapeante")
//    default Capeante convertToCapeante(RestCapeante restCapeante) {
//        if (restCapeante instanceof RestCapeantePSAmbulatorio) {
//            return CapeantePSAmbulatorioMapper.INSTANCE.convertToModel((RestCapeantePSAmbulatorio) restCapeante);
//        }
//        if(restCapeante instanceof RestCapeanteInternacao){
//            return CapeanteInternacaoMapper.INSTANCE.convertToModel((RestCapeanteInternacao) restCapeante);
//        }
//        return null;
//    }
//}
