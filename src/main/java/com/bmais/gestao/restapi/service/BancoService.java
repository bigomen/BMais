package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.Banco;
import com.bmais.gestao.restapi.repository.BancoRepository;
import com.bmais.gestao.restapi.restmodel.RestBanco;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BancoService extends com.bmais.gestao.restapi.service.Service<Banco, RestBanco>{

    private final BancoRepository bancoRepository;

    @Autowired
    public BancoService(BancoRepository bancoRepository) {
        super();
        this.bancoRepository = bancoRepository;
    }

    public Collection<RestBanco> listar() {
        return super.getAll();
    }

    @Override
    protected CrudRepository<Banco, Long> getRepository() {
        return bancoRepository;
    }
}
