package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.CadastroJaExistente;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Internacao;
import com.bmais.gestao.restapi.model.StatusInternacao;
import com.bmais.gestao.restapi.repository.InternacaoRepository;
import com.bmais.gestao.restapi.restmodel.RestInternacao;
import com.bmais.gestao.restapi.restmodel.RestInternacaoPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class InternacaoService{

    private final InternacaoRepository internacaoRepository;

    @Autowired
    public InternacaoService(InternacaoRepository internacaoRepository) {
        super();
        this.internacaoRepository = internacaoRepository;
    }

    public Collection<RestInternacao> lista(RestInternacaoPesquisa params){
        Collection<Internacao> internacoes = internacaoRepository.lista(params);
        return internacoes.stream().map(Internacao::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestInternacao> listaAtiva(RestInternacaoPesquisa params){
        params.setStatus(StatusInternacao.ATIVO);
        Collection<Internacao> internacoes = internacaoRepository.lista(params);
        return internacoes.stream().map(Internacao::modelParaRest).collect(Collectors.toList());
    }

    
    public RestInternacao detalhes(String id){
        if(!internacaoRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Internacao internacao = internacaoRepository.detalhes(UtilSecurity.decryptId(id));
        return internacao.modelParaRest();
    }

    public RestInternacao novo(RestInternacao restInternacao){
//        restInternacao.setNumeroInternacao(internacaoRepository.numeroInternacao());,
        if(internacaoRepository.validarInternacao(UtilSecurity.decryptId(restInternacao.getPaciente().getId()))){
            throw new CadastroJaExistente("O paciente selecionado está com uma internação ativa!");
        }
        Internacao internacao = internacaoRepository.save(restInternacao.restParaModel());
        return internacao.modelParaSimpleRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestInternacao restInternacao, String id){
        if(!internacaoRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
//        restInternacao.setNumeroInternacao(internacaoRepository.getNumeroInternacao(UtilSecurity.decryptId(id)));
        restInternacao.setId(id);
        
        Internacao i = restInternacao.restParaModel();
        if (i.getObito().booleanValue() || i.getDataHoraAlta() != null)
            i.setStatus(new StatusInternacao(StatusInternacao.INATIVO));
        else if (!i.getObito().booleanValue()  && i.getDataHoraAlta() == null)
            i.setStatus(new StatusInternacao(StatusInternacao.ATIVO));

        internacaoRepository.save(i);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        Optional<Internacao> internacao = internacaoRepository.findById(UtilSecurity.decryptId(id));
        if(internacao.isEmpty()){
            throw new ObjectNotFoundException("Internação não encontrada!");
        }
        internacaoRepository.delete(internacao.get());
    }
}
