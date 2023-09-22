package com.bmais.gestao.restapi.service;


import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.model.RamoAtividade;
import com.bmais.gestao.restapi.repository.RamoAtividadeRepository;
import com.bmais.gestao.restapi.restmodel.RestRamoAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RamoAtividadeService extends com.bmais.gestao.restapi.service.Service<RamoAtividade, RestRamoAtividade>{

    private final RamoAtividadeRepository ramoAtividadeRepository;

    @Autowired
    public RamoAtividadeService(RamoAtividadeRepository ramoAtividadeRepository) {
        super();
        this.ramoAtividadeRepository = ramoAtividadeRepository;
    }

    public Collection<RestRamoAtividade> listar() {
        Collection<RamoAtividade> ramos = ramoAtividadeRepository.findAllByOrderByDescricaoAsc();
        return ramos.stream().map(RamoAtividade::modelParaRest).collect(Collectors.toList());
    }

    public void cadastrar(RestRamoAtividade restRamoAtividade){
        if(ramoAtividadeRepository.existsByDescricao(restRamoAtividade.getDescricao())){
            throw new ObjectAlreadyInBase(MensagensID.JaCadastrado);
        }
        ramoAtividadeRepository.save(restRamoAtividade.restParaModel());
    }

    @Override
    protected CrudRepository<RamoAtividade, Long> getRepository() {
        return ramoAtividadeRepository;
    }
}
