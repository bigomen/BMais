//package com.bmais.gestao.restapi.service;
//
//import com.bmais.gestao.restapi.constants.MensagensID;
//import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
//import com.bmais.gestao.restapi.model.Filial;
//import com.bmais.gestao.restapi.model.StatusFilial;
//import com.bmais.gestao.restapi.model.Usuario;
//import com.bmais.gestao.restapi.repository.FilialRepository;
//import com.bmais.gestao.restapi.restmodel.RestFilial;
//import com.bmais.gestao.restapi.restmodel.RestUsuario;
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
//public class FilialService extends com.bmais.gestao.restapi.service.Service<Filial, RestFilial> {
//
//    private final FilialRepository filialRepository;
//
//    @Autowired
//    public FilialService(FilialRepository filialRepository) {
//        super();
//        this.filialRepository = filialRepository;
//    }
//
//    public RestFilial detalhes(String id) {
//        return super.getById(id);
//    }
//
//    public Collection<RestFilial> pesquisar() {
//        Collection<Filial> filiais = filialRepository.pesquisar();
//        return filiais.stream().map(Filial::modelParaRest).collect(Collectors.toList());
//    }
//
//    public RestFilial novo(RestFilial restFilial) {
//        return super.createOrUpdate(restFilial);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void alterar(RestFilial restFilial, String id) {
//        boolean existsById = filialRepository.existsById(UtilSecurity.decryptId(id));
//        if (!existsById) {
//            throw new ObjectNotFoundException(MensagensID.PTR100);
//        }
//        Filial filial = restFilial.restParaModel();
//        filial.setId(UtilSecurity.decryptId(id));
//        filialRepository.save(filial);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void deletar(String id) {
//        Filial filial = filialRepository.findById(UtilSecurity.decryptId(id))
//                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR100));
//        filial.setStatus(new StatusFilial(StatusFilial.INATIVO));
//        filialRepository.save(filial);
//    }
//
//    @Override
//    protected CrudRepository<Filial, Long> getRepository() {
//        return filialRepository;
//    }
//}
