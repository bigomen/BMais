package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.Escolaridade;
import com.bmais.gestao.restapi.repository.EscolaridadeRepository;
import com.bmais.gestao.restapi.restmodel.RestEscolaridade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EscolaridadeService extends com.bmais.gestao.restapi.service.Service<Escolaridade, RestEscolaridade> {

    private final EscolaridadeRepository escolaridadeRepository;

    @Autowired
    public EscolaridadeService(EscolaridadeRepository escolaridadeRepository) {
        super();
        this.escolaridadeRepository = escolaridadeRepository;
    }

    public Collection<RestEscolaridade> listar() {
        return super.getAll();
    }

    @Override
    protected CrudRepository<Escolaridade, Long> getRepository() {
        return escolaridadeRepository;
    }
}
