package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.SintomaQuadroClinico;
import com.bmais.gestao.restapi.repository.SintomaQuadroClinicoRepository;
import com.bmais.gestao.restapi.restmodel.RestSintomaQuadroClinico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SintomaQuadroClinicoService {

    private final SintomaQuadroClinicoRepository sintomaQuadroClinicoRepository;

    @Autowired
    public SintomaQuadroClinicoService(SintomaQuadroClinicoRepository sintomaQuadroClinicoRepository) {
        super();
        this.sintomaQuadroClinicoRepository = sintomaQuadroClinicoRepository;
    }

    public Collection<RestSintomaQuadroClinico> lista(){
        Collection<SintomaQuadroClinico> sintomaQuadroClinicos = sintomaQuadroClinicoRepository.lista();
        return sintomaQuadroClinicos.stream().map(SintomaQuadroClinico::modelParaRest).collect(Collectors.toList());
    }
}
