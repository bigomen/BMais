//package com.bmais.gestao.restapi.aop;
//
//import com.bmais.gestao.restapi.model.enums.TipoHistorico;
//import com.bmais.gestao.restapi.restmodel.RestCapeante;
//import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.service.CapeanteInternacaoService;
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
//public class HistoricoCapeanteInternacaoAOP {
//
//    private final HistoricoCapeanteService historicoCapeanteService;
//    private final CapeanteInternacaoService capeanteInternacaoService;
//
//
//    @Autowired
//    public HistoricoCapeanteInternacaoAOP(HistoricoCapeanteService historicoCapeanteService, CapeanteInternacaoService capeanteInternacaoService) {
//        this.historicoCapeanteService = historicoCapeanteService;
//        this.capeanteInternacaoService = capeanteInternacaoService;
//    }
//
//    @AfterReturning(pointcut="execution(* com.bmais.gestao.restapi.service.CapeanteInternacaoService.novo(..))",
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
//    @AfterReturning(value="execution(* com.bmais.gestao.restapi.service.CapeanteInternacaoService.atualizar(..))" +
//            " and args(restCapeanteInternacao, id)")
//    public void logAfterReturningUpdate(RestCapeanteInternacao restCapeanteInternacao, String id) {
//        RestCapeanteInternacao byId = capeanteInternacaoService.dadosRelatorio(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(byId);
//        restHistoricoCapeante.setUsuario(byId.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.A);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//
//    @AfterReturning(value="execution(* com.bmais.gestao.restapi.service.CapeanteInternacaoService.atualizar(..)) and args(id)")
//    public void logAfterReturningUpdate(String id) {
//        RestCapeanteInternacao restCapeantePSAmbulatorio = capeanteInternacaoService.dadosRelatorio(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(restCapeantePSAmbulatorio);
//        restHistoricoCapeante.setUsuario(restCapeantePSAmbulatorio.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.F);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//
//    @AfterReturning(pointcut="execution(* com.bmais.gestao.restapi.service.CapeanteInternacaoService.apagar(..))")
//    public void logAfterReturningDeletar(JoinPoint joinPoint) {
//        String id = (String) joinPoint.getArgs()[0];
//
//        RestCapeanteInternacao restCapeantePSAmbulatorio = capeanteInternacaoService.dadosRelatorio(id);
//        RestHistoricoCapeante restHistoricoCapeante = new RestHistoricoCapeante();
//        restHistoricoCapeante.setCapeante(restCapeantePSAmbulatorio);
//        restHistoricoCapeante.setUsuario(restCapeantePSAmbulatorio.getUsuario());
//        restHistoricoCapeante.setTipoHistorico(TipoHistorico.R);
//        restHistoricoCapeante.setData(UtilData.obterDataHoraAtual());
//
//        historicoCapeanteService.novo(restHistoricoCapeante);
//    }
//}
