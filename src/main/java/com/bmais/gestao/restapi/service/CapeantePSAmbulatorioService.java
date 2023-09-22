package com.bmais.gestao.restapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.model.StatusCapeante;
import com.bmais.gestao.restapi.model.projections.CapeantePSAmbulatorioProjection;
import com.bmais.gestao.restapi.repository.CapeantePSAmbulatorioRepository;
import com.bmais.gestao.restapi.restmodel.RestCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.restmodel.RestCapeantePesquisa;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CapeantePSAmbulatorioService {

    private final CapeantePSAmbulatorioRepository capeantePSAmbulatorioRepository;

    @Autowired
    public CapeantePSAmbulatorioService(CapeantePSAmbulatorioRepository capeantePSAmbulatorioRepository) {
        super();
        this.capeantePSAmbulatorioRepository = capeantePSAmbulatorioRepository;
    }

    public RestCapeantePSAmbulatorio detalhes(String id) {
        if(!capeantePSAmbulatorioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        CapeantePSAmbulatorio capeante = capeantePSAmbulatorioRepository.detalhes(UtilSecurity.decryptId(id))
        		.orElseThrow(() -> new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado));
        return capeante.modelParaRest();
    }

    public Collection<RestCapeantePSAmbulatorio> listar(RestCapeantePesquisa params) {
        Collection<CapeantePSAmbulatorioProjection> capeantes = capeantePSAmbulatorioRepository.lista(params);
        return capeantes.stream().map(CapeantePSAmbulatorioProjection::projectionToRest).collect(Collectors.toList());
    }

    public void novo(RestCapeantePSAmbulatorio restCapeantePSAmbulatorio) {
        CapeantePSAmbulatorio capeante = restCapeantePSAmbulatorio.restParaModel();
        capeante.setStatus(new StatusCapeante(StatusCapeante.PENDENTE));
        capeantePSAmbulatorioRepository.save(capeante);
    }

    public void novoRascunho(RestCapeantePSAmbulatorio restCapeantePSAmbulatorio) {
        CapeantePSAmbulatorio capeante = restCapeantePSAmbulatorio.restParaModel();
        capeante.setStatus(new StatusCapeante(StatusCapeante.RASCUNHO));
        capeantePSAmbulatorioRepository.save(capeante);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestCapeantePSAmbulatorio restCapeantePSAmbulatorio, String id)
    {
        if(!capeantePSAmbulatorioRepository.existsById(UtilSecurity.decryptId(id)))
        {
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        
        CapeantePSAmbulatorio capeante = restCapeantePSAmbulatorio.restParaModel();
        capeante.setId(UtilSecurity.decryptId(id));
        capeantePSAmbulatorioRepository.save(capeante);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void aprovar(String id) {
        if(!capeantePSAmbulatorioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if(RuleUtil.getGrupoId() != GrupoUsuario.GERENTE){
            throw new IncorrectData(MensagensID.SemPermissao);
        }
        capeantePSAmbulatorioRepository.alterar(UtilSecurity.decryptId(id), StatusCapeante.APROVADO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reprovar(String id) {
        if(!capeantePSAmbulatorioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if(RuleUtil.getGrupoId() != GrupoUsuario.GERENTE){
            throw new IncorrectData(MensagensID.SemPermissao);
        }
        capeantePSAmbulatorioRepository.alterar(UtilSecurity.decryptId(id), StatusCapeante.RECUSADO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id) {
        if(!capeantePSAmbulatorioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        capeantePSAmbulatorioRepository.alterar(UtilSecurity.decryptId(id), StatusCapeante.EXCLUIDO);
    }
}
