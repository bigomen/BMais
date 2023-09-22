package com.bmais.gestao.restapi.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.model.StatusProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.repository.ProntuarioVisitaHomeCareRepository;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCarePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProntuarioVisitaHomeCareService{

    private final ProntuarioVisitaHomeCareRepository prontuarioRepository;

    @Autowired
    public ProntuarioVisitaHomeCareService(ProntuarioVisitaHomeCareRepository prontuarioRepository) {
        super();
        this.prontuarioRepository = prontuarioRepository;
    }

    public Collection<RestProntuarioVisitaHomeCare> lista(RestProntuarioVisitaHomeCarePesquisa params){
        Collection<ProntuarioVisitaHomeCare> prontuarios = prontuarioRepository.lista(params);
        return prontuarios.stream().map(ProntuarioVisitaHomeCare::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestProntuarioVisitaHomeCare> listaAtiva(RestProntuarioVisitaHomeCarePesquisa params){
    	params.setStatus(String.valueOf(StatusProntuarioVisitaHomeCare.ATIVO));
        Collection<ProntuarioVisitaHomeCare> prontuarios = prontuarioRepository.lista(params);
        return prontuarios.stream().map(ProntuarioVisitaHomeCare::modelParaRest).collect(Collectors.toList());
    }

    
    public RestProntuarioVisitaHomeCare detalhes(String id){
        if(!prontuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        ProntuarioVisitaHomeCare prontuario = prontuarioRepository.detalhes(UtilSecurity.decryptId(id));
        return prontuario.modelParaRest();
    }

    public void novo(RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCareweb){
        prontuarioRepository.save(restProntuarioVisitaHomeCareweb.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestProntuarioVisitaHomeCare restProntuarioVisitaHomeCare, String id){
        if(!prontuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        restProntuarioVisitaHomeCare.setId(id);
        
        ProntuarioVisitaHomeCare i = restProntuarioVisitaHomeCare.restParaModel();
        if (i.getObito().booleanValue() || i.getAlta() != null)
        	i.setStatus(new StatusProntuarioVisitaHomeCare(StatusProntuarioVisitaHomeCare.INATIVO));
        else if (!i.getObito().booleanValue()  && i.getAlta() == null)
        	i.setStatus(new StatusProntuarioVisitaHomeCare(StatusProntuarioVisitaHomeCare.ATIVO));
        		
        prontuarioRepository.save(i);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        if(!prontuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        prontuarioRepository.desativar(UtilSecurity.decryptId(id));
    }
}
