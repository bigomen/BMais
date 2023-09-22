//package com.bmais.gestao.restapi.aop;
//
//import com.bmais.gestao.restapi.model.enums.TipoHistorico;
//import com.bmais.gestao.restapi.restmodel.RestCapeante;
//import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService;
//import com.bmais.gestao.restapi.service.HistoricoCapeanteService;
//import com.bmais.gestao.restapi.utility.UtilData;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Slf4j
//public class HistoricoCapeanteAOP {
//
//    private final HistoricoCapeanteService historicoCapeanteService;
//    private final CapeantePSAmbulatorioService capeantePSAmbulatorioService;
//
//    @Autowired
//    public HistoricoCapeanteAOP(HistoricoCapeanteService historicoCapeanteService, CapeantePSAmbulatorioService capeantePSAmbulatorioService) {
//        this.historicoCapeanteService = historicoCapeanteService;
//        this.capeantePSAmbulatorioService = capeantePSAmbulatorioService;
//    }
//
//    @AfterReturning(pointcut="execution(* com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService.novo(..))",
//            returning = "returnValue")
//    public void logAfterReturningNovo(RestCapeante returnValue) {
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(returnValue);
//        restHistoricoCapeante.setUsuario(returnValue.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.I);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//
//
//
//    @AfterReturning(value="execution(* com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService.update(..))" +
//            " and args(restCapeantePSAmbulatorio, id)")
//    public void logAfterReturningUpdate(RestCapeantePSAmbulatorio restCapeantePSAmbulatorio, String id) {
//        RestCapeantePSAmbulatorio byId = capeantePSAmbulatorioService.detalhes(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(byId);
//        restHistoricoCapeante.setUsuario(byId.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.A);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//
//    @AfterReturning(value="execution(* com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService.update(..)) and args(id)")
//    public void logAfterReturningUpdate(String id) {
//        RestCapeantePSAmbulatorio restCapeantePSAmbulatorio = capeantePSAmbulatorioService.detalhes(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(restCapeantePSAmbulatorio);
//        restHistoricoCapeante.setUsuario(restCapeantePSAmbulatorio.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.F);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//
//    @AfterReturning(pointcut="execution(* com.bmais.gestao.restapi.service.CapeantePSAmbulatorioService.deletar(..))")
//    public void logAfterReturningDeletar(JoinPoint joinPoint) {
//        String id = (String) joinPoint.getArgs()[0];
//
//        RestCapeantePSAmbulatorio restCapeantePSAmbulatorio = capeantePSAmbulatorioService.detalhes(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(restCapeantePSAmbulatorio);
//        restHistoricoCapeante.setUsuario(restCapeantePSAmbulatorio.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.R);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//}
//
