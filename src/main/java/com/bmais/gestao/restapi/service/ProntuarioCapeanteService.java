package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeante;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeantePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProntuarioCapeanteService {

    private final ProntuarioCapeanteRepository capeanteInternacaoRepository;
    private final AuditorRepository auditorRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PacienteRepository pacienteRepository;
    private final HospitalRepository hospitalRepository;
    private final CIDRepository cidRepository;

    @Autowired
    public ProntuarioCapeanteService(ProntuarioCapeanteRepository capeanteInternacaoRepository, AuditorRepository auditorRepository, UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, PacienteRepository pacienteRepository, HospitalRepository hospitalRepository, CIDRepository cidRepository) {
        super();
        this.capeanteInternacaoRepository = capeanteInternacaoRepository;
        this.auditorRepository = auditorRepository;
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.pacienteRepository = pacienteRepository;
        this.hospitalRepository = hospitalRepository;
        this.cidRepository = cidRepository;
    }

    public Collection<RestProntuarioCapeante> listar(RestProntuarioCapeantePesquisa params){
        Collection<ProntuarioCapeante> capeanteInternacao = capeanteInternacaoRepository.pesquisarCapeanteInternacao(params);
        return capeanteInternacao.stream().map(ProntuarioCapeante::modelParaRest).collect(Collectors.toList());
    }

    public RestProntuarioCapeante dadosRelatorio(String id){
        ProntuarioCapeante capeante = capeanteInternacaoRepository.dadosRelatorio(UtilSecurity.decryptId(id));
        return capeante.modelParaRest();
    }

    public RestProntuarioCapeante detalhes(String id){
        Optional<ProntuarioCapeante> capeante = capeanteInternacaoRepository.detalharCapeanteInternacao(UtilSecurity.decryptId(id));
        if(capeante.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        RestProntuarioCapeante restCapeanteInternacao = capeante.get().modelParaRest();
        return restCapeanteInternacao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public HashMap<String, String> novo(RestProntuarioCapeante restCapeanteInternacao)
    {
        ProntuarioCapeante prontuario = restCapeanteInternacao.restParaModel();
        capeanteInternacaoRepository.save(prontuario);
        HashMap<String, String> capeante = new HashMap<>();
        capeante.put("id", UtilSecurity.encryptId(prontuario.getId()));
        return capeante;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestProntuarioCapeante restCapeanteInternacao, String id){
        if(!capeanteInternacaoRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        restCapeanteInternacao.setId(id);
        capeanteInternacaoRepository.save(restCapeanteInternacao.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        if(!capeanteInternacaoRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        capeanteInternacaoRepository.desativar(UtilSecurity.decryptId(id));
    }

}
