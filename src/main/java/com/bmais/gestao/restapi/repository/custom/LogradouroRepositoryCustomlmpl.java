package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Logradouro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@org.springframework.stereotype.Repository
public class LogradouroRepositoryCustomlmpl implements LogradouroRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Logradouro> listar(){
        return manager.createQuery("from Logradouro",Logradouro.class)
                .getResultList();
    }

    @Override
    public Logradouro buscar(Long id){return manager.find(Logradouro.class, id);}
}
