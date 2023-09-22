package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.ConclusaoAuditoriaHC;
import com.bmais.gestao.restapi.repository.ConclusaoAuditoriaHCRepository;
import com.bmais.gestao.restapi.restmodel.RestConclusaoAuditoriaHC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConclusaoAuditoriaHCService {

    private final ConclusaoAuditoriaHCRepository conclusaoAuditoriaHCRepository;

    @Autowired
    public ConclusaoAuditoriaHCService(ConclusaoAuditoriaHCRepository conclusaoAuditoriaHCRepository) {
        super();
        this.conclusaoAuditoriaHCRepository = conclusaoAuditoriaHCRepository;
    }

    public Collection<RestConclusaoAuditoriaHC> lista(){
        Collection<ConclusaoAuditoriaHC> conclusaoAuditorias = conclusaoAuditoriaHCRepository.lista();
        return conclusaoAuditorias.stream().map(ConclusaoAuditoriaHC::modelParaRest).collect(Collectors.toList());
    }
}
