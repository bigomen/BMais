package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Collection;

public class MunicipioRepositoryCustomImpl extends com.bmais.gestao.restapi.repository.custom.Repository<Municipio> implements MunicipioRepositoryCustom {

    @Override
    public Class<Municipio> getClazz(){return Municipio.class;}
}
