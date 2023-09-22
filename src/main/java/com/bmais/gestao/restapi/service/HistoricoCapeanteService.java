//package com.bmais.gestao.restapi.service;
//
//import com.bmais.gestao.restapi.model.HistoricoCapeante;
//import com.bmais.gestao.restapi.repository.HistoricoCapeanteRepository;
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.utility.UtilSecurity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//public class HistoricoCapeanteService extends com.bmais.gestao.restapi.service.Service<HistoricoCapeante, RestHistoricoCapeante> {
//
//    private final HistoricoCapeanteRepository historicoCapeanteRepository;
//
//    @Autowired
//    public HistoricoCapeanteService(HistoricoCapeanteRepository historicoCapeanteRepository) {
//        super();
//        this.historicoCapeanteRepository = historicoCapeanteRepository;
//    }
//
//    public Collection<RestHistoricoCapeante> listar(String idCapeante) {
//        Collection<HistoricoCapeante> historicosCapeante = historicoCapeanteRepository.listarHistoricoCapeante(UtilSecurity.decryptId(idCapeante));
//        return historicosCapeante.stream().map(hc -> hc.modelParaRest()).collect(Collectors.toList());
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public RestHistoricoCapeante novo(RestHistoricoCapeante restHistoricoCapeante) {
//        HistoricoCapeante historicoCapeante = historicoCapeanteRepository.save(restHistoricoCapeante.restParaModel());
//        return historicoCapeante.modelParaRest();
//    }
//
//    @Override
//    protected CrudRepository<HistoricoCapeante, Long> getRepository() {
//        return historicoCapeanteRepository;
//    }
//}
