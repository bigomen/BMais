package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.CadastroJaExistente;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.bmais.gestao.restapi.repository.ClienteServicosRepository;
import com.bmais.gestao.restapi.repository.HospitalClienteRepository;
import com.bmais.gestao.restapi.repository.HospitalRepository;
import com.bmais.gestao.restapi.repository.VinculosRepository;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.restmodel.RestHospitalCliente;
import com.bmais.gestao.restapi.restmodel.RestHospitalPesquisa;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import com.bmais.gestao.restapi.security.JWTAuthentication;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class HospitalService{

    private final HospitalRepository hospitalRepository;
    private final HospitalClienteRepository hospitalclienteRepository;
    private final VinculosRepository vinculosRepository;

    private final ClienteServicosRepository clienteServicosRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, HospitalClienteRepository hospitalclienteRepository, VinculosRepository vinculosRepository, ClienteServicosRepository clienteServicosRepository) {
        this.hospitalRepository = hospitalRepository;
		this.hospitalclienteRepository = hospitalclienteRepository;
        this.vinculosRepository = vinculosRepository;
        this.clienteServicosRepository = clienteServicosRepository;
    }

    public Collection<RestHospital> listar(RestHospitalPesquisa params) {
        Collection<Hospital> hospitais = hospitalRepository.listar(params);
        return hospitais.stream().map(Hospital::modelParaRest).collect(Collectors.toList());
    }
    
    public Collection<RestHospital> pesquisarHospitaisVinculos(String cliente)
    {
    	Set<Hospital> hospitais = hospitalRepository.pesquisarHospitaisVinculos(UtilSecurity.decryptId(cliente));
    	
    	return hospitais.stream()
    			.map(Hospital::modelParaRest)
    			.collect(Collectors.toList());
    }
    
    public Collection<RestHospitalCliente> listar(String cliente)
    {
    	Collection<HospitalCliente> hospitaisPorPaciente = hospitalclienteRepository.listaHospitaisCliente(UtilSecurity.decryptId(cliente));
    	
    	return hospitaisPorPaciente.stream()
    			.map(HospitalCliente::modelParaRest)
    			.collect(Collectors.toList());
    }

    public Collection<RestHospitalCliente> hospitaisVisitaConcorrente(String cliente, Long tipo)
    {
        Collection<HospitalCliente> hospitaisPorPaciente = hospitalclienteRepository.hospitaisVisitaConcorrente(UtilSecurity.decryptId(cliente), tipo);

        return hospitaisPorPaciente.stream()
                .map(HospitalCliente::modelParaRest)
                .collect(Collectors.toList());
    }

    public RestHospital detalhes(String id){
        if(!hospitalRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Hospital hospital = hospitalRepository.detalhes(UtilSecurity.decryptId(id));
        return hospital.modelParaRest();
    }

    public void novo(RestHospital restHospital){
        if(hospitalRepository.existsByRazaoSocial(restHospital.getRazaoSocial())){
            throw new CadastroJaExistente(MensagensID.RazaoSocialExiste);
        }
        if(hospitalRepository.existsByCnpj(restHospital.getCnpj())) {
            throw new CadastroJaExistente(MensagensID.CNPJExiste);
        }
        restHospital.setClientes(null);
        hospitalRepository.save(restHospital.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestHospital restHospital, String id){
        if(!hospitalRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        restHospital.setId(id);
        Hospital hospital = restHospital.restParaModel();
        Collection<HospitalCliente> clientes = hospitalclienteRepository.findByHospital(hospital);
        if(clientes.size() > 0)
            hospital.setClientes(clientes);
        hospitalRepository.save(hospital);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        if(!hospitalRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        hospitalRepository.desativarHospital(UtilSecurity.decryptId(id));
    }

    public Collection<RestHospital> listaSimples(){
        Collection<Hospital> hospitais = hospitalRepository.listaSimples();
        return hospitais.stream().map(Hospital::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestVinculo> vinculos(String id){
        Collection<Vinculo> vinculos = vinculosRepository.hospitalVinculos(UtilSecurity.decryptId(id), StatusPessoaJuridica.ATIVO, StatusAuditor.ATIVO);
        return vinculos.stream().map(Vinculo::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestHospital> listaHospitaisVisitaConcorrente(){
        JWTAuthentication usuarioLogado = RuleUtil.getUsuarioLogado();
        if(StringUtils.equals(usuarioLogado.getControleAcesso().getCategoria(), CategoriaGrupoUsuario.AU.name()))
        {
            Collection<Long> hospitais = new ArrayList<>();
            usuarioLogado.getControleAcesso().getVinculos().forEach(v -> {
                if(v.getHospitalId() != null){
                    hospitais.add(v.getHospitalId());
                }
            });
            Collection<Long> hospitaisId = hospitais.stream().distinct().collect(Collectors.toList());
            Collection<Hospital> hospitals = hospitalRepository.listaHospitaisVisitaConcorrente(hospitaisId);
            return hospitals.stream().map(Hospital::modelParaRest).collect(Collectors.toList());
        }else {
            return listaSimples();
        }
    }

}
