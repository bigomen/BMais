package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.model.StatusVisita;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import com.bmais.gestao.restapi.repository.ProntuarioVisitaHomeCareRepository;
import com.bmais.gestao.restapi.repository.VisitaHomecareRepository;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomecarePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class VisitaHomecareService {

    private final VisitaHomecareRepository visitaHomecareRepository;
    private final ProntuarioVisitaHomeCareRepository prontuarioRepository;

    @Autowired
    public VisitaHomecareService(VisitaHomecareRepository visitaHomecareRepository, ProntuarioVisitaHomeCareRepository prontuarioRepository) {
        super();
        this.visitaHomecareRepository = visitaHomecareRepository;
		this.prontuarioRepository = prontuarioRepository;
    }

    public Collection<RestVisitaHomeCare> lista(RestVisitaHomecarePesquisa params){
        Collection<VisitaHomeCare> visitas = visitaHomecareRepository.lista(params);
        return visitas.stream().map(VisitaHomeCare::modelParaRest).collect(Collectors.toList());
    }

    public RestVisitaHomeCare detalhes(String id) {
        if(!visitaHomecareRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        VisitaHomeCare visita = visitaHomecareRepository.detalhes(UtilSecurity.decryptId(id));
        Optional<ProntuarioVisitaHomeCare> ultimoProntuarioPaciente = prontuarioRepository.pesquisarUltimoProntuarioPaciente(visita.getProntuario().getPaciente());
        Optional<VisitaHomeCare> ultimaVisita = visitaHomecareRepository.pesquisarUltimaVisitaProntuario(visita.getProntuario());
        RestVisitaHomeCare restVisitaHomeCare = visita.modelParaRest();
        
        ultimoProntuarioPaciente.ifPresent(p -> restVisitaHomeCare.getProntuario().getPaciente().setDataUltimoProntuario(p.getDataInclusao()));
        ultimaVisita.ifPresent(v -> restVisitaHomeCare.getProntuario().getPaciente().setDataUltimaVisita(v.getDataInclusao()));
        
        return restVisitaHomeCare;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestVisitaHomeCare restVisitaHomeCare)
    {
        Optional<ProntuarioVisitaHomeCare> prontuario = prontuarioRepository.findById(UtilSecurity.decryptId(restVisitaHomeCare.getProntuario().getId()));
        if(prontuario.isEmpty()){
            throw new ObjectNotFoundException("Prontuario não encontrado!");
        }
        prontuario.get().setAlta(restVisitaHomeCare.getProntuario().getAlta());
        if(Objects.nonNull(restVisitaHomeCare.getProntuario().getMotivoAlta().getId())){
            prontuario.get().setMotivoAlta(restVisitaHomeCare.getProntuario().getMotivoAlta().restParaModel());
        }else{
            prontuario.get().setMotivoAlta(null);
        }
        prontuario.get().setObito(restVisitaHomeCare.getProntuario().getObito());
        if(Objects.nonNull(prontuario.get().getAlta()) && Objects.isNull(prontuario.get().getMotivoAlta())){
            throw new IncorrectData("Motivo Alta obrigatorio!");
        }else if(prontuario.get().getObito()){
            if(Objects.isNull(prontuario.get().getMotivoAlta()) || Objects.isNull(prontuario.get().getAlta())){
                throw new IncorrectData("Motivo Alta e Data Alta obrigatorios!");
            }
        }else if(Objects.nonNull(prontuario.get().getMotivoAlta()) && Objects.isNull(prontuario.get().getAlta())){
            throw new IncorrectData("Data Alta obrigatorio!");
        }
        VisitaHomeCare visitaHomeCare = restVisitaHomeCare.restParaModel();
        visitaHomeCare.setProntuario(prontuario.get());
		visitaHomecareRepository.save(visitaHomeCare);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestVisitaHomeCare restVisitaHomeCare, String id){
        if(!visitaHomecareRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        Optional<ProntuarioVisitaHomeCare> prontuario = prontuarioRepository.findById(UtilSecurity.decryptId(restVisitaHomeCare.getProntuario().getId()));
        if(prontuario.isEmpty()){
            throw new ObjectNotFoundException("Prontuario não encontrado!");
        }

        prontuario.get().setAlta(restVisitaHomeCare.getProntuario().getAlta());
        if(Objects.nonNull(restVisitaHomeCare.getProntuario().getMotivoAlta().getId())){
            prontuario.get().setMotivoAlta(restVisitaHomeCare.getProntuario().getMotivoAlta().restParaModel());
        }else{
            prontuario.get().setMotivoAlta(null);
        }
        prontuario.get().setObito(restVisitaHomeCare.getProntuario().getObito());

        if(Objects.nonNull(prontuario.get().getAlta()) && Objects.isNull(prontuario.get().getMotivoAlta())){
            throw new IncorrectData("Motivo Alta obrigatorio!");
        }else if(prontuario.get().getObito()){
            if(Objects.isNull(prontuario.get().getMotivoAlta()) || Objects.isNull(prontuario.get().getAlta())){
                throw new IncorrectData("Motivo Alta e Data Alta obrigatorios!");
            }
        }else if(Objects.nonNull(prontuario.get().getMotivoAlta()) && Objects.isNull(prontuario.get().getAlta())){
            throw new IncorrectData("Data Alta obrigatorio!");
        }

//        VisitaHomeCare visita = visitaHomecareRepository.findNumero(UtilSecurity.decryptId(id));
        restVisitaHomeCare.setId(id);
//        restVisitaHomeCare.setNumeroVisita(visita.getNumeroVisita());
        VisitaHomeCare visitaHomeCare = restVisitaHomeCare.restParaModel();
        visitaHomeCare.setProntuario(prontuario.get());
//        visitaHomeCare.setStatus(new StatusVisita(1L));
        visitaHomecareRepository.save(visitaHomeCare);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        Long idVisita = UtilSecurity.decryptId(id);
        if(!visitaHomecareRepository.existsById(idVisita)){
            throw new ObjectNotFoundException("Visita não encontrada");
        }
        Long idProntuario = visitaHomecareRepository.getProntuarioId(idVisita);
        if(visitaHomecareRepository.validarExclusao(idVisita, idProntuario)){
            throw new IncorrectData("A exclusão de visitas de prontuário deve ser feita por ordem de inserção no sistema");
        }
        visitaHomecareRepository.deleteById(idVisita);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void finalizar(String id)
    {
        if(!visitaHomecareRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        visitaHomecareRepository.atualizarStatus(UtilSecurity.decryptId(id), StatusVisita.APROVADO);
    }

//    public RestPacienteAuditoria detalhesPaciente(String id){
//        if(!pacienteRepository.existsById(UtilSecurity.decryptId(id))){
//            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
//        }
//        RestPacienteAuditoria paciente = new RestPacienteAuditoria();
//        paciente.setNumeroVisitas(visitaHomecareRepository.numeroVisitasPaciente(UtilSecurity.decryptId(id)));
//        if(paciente.getNumeroVisitas() != null){
//            paciente.setUltimaVisita(visitaHomecareRepository.ultimaVisita(UtilSecurity.decryptId(id)));
//            paciente.setUltimoProntuario(visitaHomecareRepository.numeroUltimaVisita(UtilSecurity.decryptId(id)));
//        }
//        paciente.setUltimaInternacao(internacaoRepository.ultimaInternacao(UtilSecurity.decryptId(id)));
//        return paciente;
//    }
}
