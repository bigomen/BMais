package com.bmais.gestao.restapi.repository.custom;


import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.model.UF;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UfRepositoryCustomlmpl implements  UfRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<UF> listar(){
        return manager.createQuery("from UF", UF.class)
                .getResultList();
    }

    @Override
    public List<Municipio> buscar(Long id){
        return manager.createQuery("from Municipio where uf_id = :value")
                .setParameter("value",id).getResultList();
    }
}
