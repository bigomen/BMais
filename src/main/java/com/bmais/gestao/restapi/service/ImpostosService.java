package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Impostos;
import com.bmais.gestao.restapi.repository.ImpostosRepository;
import com.bmais.gestao.restapi.restmodel.RestImpostos;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImpostosService {

    private final ImpostosRepository impostosRepository;

    @Autowired
    public ImpostosService(ImpostosRepository impostosRepository) {
        this.impostosRepository = impostosRepository;
    }

    public Collection<RestImpostos> valores(){
        Collection<Impostos> impostos = impostosRepository.valores();
        return impostos.stream().map(imp -> imp.modelParaRest()).collect(Collectors.toList());
    }

    public void atualizar(Collection<RestImpostos> restImpostos){
//        Collection<Impostos> impostos = impostosRepository.valores();
        ArrayList<Impostos> impostos = new ArrayList<Impostos>();
        for (RestImpostos i: restImpostos){
            Optional<Impostos> imp = impostosRepository.findById(UtilSecurity.decryptId(i.getId()));
            if(imp.isEmpty()){
                throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
            }else {
                imp.get().setValor(i.getValor());
                impostos.add(imp.get());
            }
        }
        impostosRepository.saveAll(impostos);
    }
}
