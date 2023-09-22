package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Beneficio;
import com.bmais.gestao.restapi.repository.BeneficioRepository;
import com.bmais.gestao.restapi.restmodel.RestBeneficio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;

    @Autowired
    public BeneficioService(BeneficioRepository beneficioRepository) {
        super();
        this.beneficioRepository = beneficioRepository;
    }

    public Collection<RestBeneficio> lista(){
        Collection<Beneficio> beneficios = new ArrayList<>();
        Iterable<Beneficio> benefs = beneficioRepository.findAll();
        benefs.forEach(beneficios::add);
        return beneficios.stream().map(Beneficio::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestBeneficio> listaSimples(){
        Collection<Beneficio> beneficios = beneficioRepository.listaSimples();
        return beneficios.stream().map(Beneficio::modelParaRest).collect(Collectors.toList());
    }

    public void novo(RestBeneficio restBeneficio){
        if(beneficioRepository.existsByDescricao(restBeneficio.getDescricao())){
            throw new ObjectAlreadyInBase(MensagensID.BeneficioExistente);
        }
        beneficioRepository.save(restBeneficio.restParaModel());
    }

    public void atualizar(RestBeneficio restBeneficio, String id){
        if(!beneficioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        restBeneficio.setId(id);
        beneficioRepository.save(restBeneficio.restParaModel());
    }

    public void apagar(String id){
        if(!beneficioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        beneficioRepository.deleteById(UtilSecurity.decryptId(id));
    }
}
