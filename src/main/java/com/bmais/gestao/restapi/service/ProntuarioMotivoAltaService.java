package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.ProntuarioMotivoAlta;
import com.bmais.gestao.restapi.repository.ProntuarioMotivoAltaRepository;
import com.bmais.gestao.restapi.restmodel.RestProntuarioMotivoAlta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProntuarioMotivoAltaService {

    private final ProntuarioMotivoAltaRepository prontuarioMotivoAltaRepository;

    @Autowired
    public ProntuarioMotivoAltaService(ProntuarioMotivoAltaRepository prontuarioMotivoAltaRepository) {
        super();
        this.prontuarioMotivoAltaRepository = prontuarioMotivoAltaRepository;
    }

    public Collection<RestProntuarioMotivoAlta> lista(){
        Collection<ProntuarioMotivoAlta> prontuarioMotivoAlta = prontuarioMotivoAltaRepository.lista();
        return prontuarioMotivoAlta.stream().map(ProntuarioMotivoAlta::modelParaRest).collect(Collectors.toList());
    }
}
