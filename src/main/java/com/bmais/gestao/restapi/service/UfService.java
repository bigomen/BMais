package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.model.UF;
import com.bmais.gestao.restapi.repository.MunicipioRepository;
import com.bmais.gestao.restapi.repository.UfRepository;
import com.bmais.gestao.restapi.restmodel.RestMunicipio;
import com.bmais.gestao.restapi.restmodel.RestUf;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UfService extends com.bmais.gestao.restapi.service.Service<UF, RestUf> {

    private final UfRepository ufRepository;

    private final MunicipioRepository municipioRepository;

    @Autowired
    public UfService(UfRepository ufRepository, MunicipioRepository municipioRepository){
        super();
        this.ufRepository = ufRepository;
        this.municipioRepository = municipioRepository;
    }

    public Collection<RestUf> listar(){
        Collection<UF> ufs = ufRepository.findAllByOrderByDescricaoAsc();
        return ufs.stream().map(UF::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestMunicipio> listarCidades(String estadoId){
        Collection<Municipio> municipios = municipioRepository.findByUfIdOrderByNome(UtilSecurity.decryptId(estadoId));
        return municipios.stream().map(Municipio::modelParaRest).collect(Collectors.toList());
    }

    @Override
    protected CrudRepository<UF, Long> getRepository(){return ufRepository;}
}
