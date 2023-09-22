package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.model.StatusPaciente;
import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestPaciente;
import com.bmais.gestao.restapi.restmodel.RestPacientePesquisa;
import com.bmais.gestao.restapi.security.JWTAuthentication;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PacienteService extends com.bmais.gestao.restapi.service.Service<Paciente, RestPaciente> {

    private final PacienteRepository pacienteRepository;
    private final VisitaHomecareRepository visitaHomecareRepository;
    private final InternacaoRepository internacaoRepository;
    private final ProntuarioCapeanteRepository capeanteInternacaoRepository;
    private final CapeantePSAmbulatorioRepository capeantePSAmbulatorioRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, VisitaHomecareRepository visitaHomecareRepository, InternacaoRepository internacaoRepository, ProntuarioCapeanteRepository capeanteInternacaoRepository, CapeantePSAmbulatorioRepository capeantePSAmbulatorioRepository) {
        super();
        this.pacienteRepository = pacienteRepository;
        this.visitaHomecareRepository = visitaHomecareRepository;
        this.internacaoRepository = internacaoRepository;
        this.capeanteInternacaoRepository = capeanteInternacaoRepository;
        this.capeantePSAmbulatorioRepository = capeantePSAmbulatorioRepository;
    }

    public Collection<RestPaciente> lista(RestPacientePesquisa params) {
        Collection<Paciente> pacientes = pacienteRepository.lista(params);
        return pacientes.stream().map(Paciente::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestPaciente> listaInternados(RestPacientePesquisa params) {
        Collection<Paciente> pacientes = pacienteRepository.listaInternados(params);
        return pacientes.stream().map(Paciente::modelParaRest).collect(Collectors.toList());
    }

    
    public Collection<RestPaciente> listaSimples(){
        Collection<Paciente> pacientes = pacienteRepository.listaSimples();
        return pacientes.stream().map(Paciente::modelParaRest).collect(Collectors.toList());
    }

    public RestPaciente detalhes(String id) {
        if(!pacienteRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Paciente paciente = pacienteRepository.detalhes(UtilSecurity.decryptId(id));
//        Collection<RestRelatorioPaciente> relatorios = new ArrayList<>();
//        Collection<VisitaHomeCare> visitas = visitaHomecareRepository.getRelatorio(paciente.getId());
//        Collection<CapeanteInternacao> capeantesInternacao = capeanteInternacaoRepository.getRelatorio(paciente.getId());
//        for (VisitaHomeCare v : visitas){
//            relatorios.add(new RestRelatorioPaciente(v.getId(), TipoRelatorio.VH, v.getDataInclusao(), v.getNumeroVisita(),v.getStatus().getDescricao()));
//        }
//        for(CapeanteInternacao c : capeantesInternacao){
//            relatorios.add(new RestRelatorioPaciente(c.getId(), TipoRelatorio.CI, c.getDataAbertura(), Long.parseLong(c.getNumero()), c.getStatus().getDescricao()));
//        }
        RestPaciente restPaciente = paciente.modelParaRest();
//        restPaciente.setRelatorios(relatorios);
        return restPaciente;
    }

    public RestPaciente detalhesMesclagem(String id){
        if(!pacienteRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Paciente paciente = pacienteRepository.detalhesMesclagem(UtilSecurity.decryptId(id));
        return paciente.modelParaRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void mesclar(Collection<RestPaciente> listaPacientes, String id){
        Paciente pacientePrincipal = pacienteRepository.getPacienteMesclagem(UtilSecurity.decryptId(id));
        for (RestPaciente p : listaPacientes){
            Paciente pac = pacienteRepository.getPacienteMesclagem(UtilSecurity.decryptId(p.getId()));
            if(pacientePrincipal.getSexo() != pac.getSexo() || !Objects.equals(pacientePrincipal.getCliente().getId(), pac.getCliente().getId())){
                throw new IncorrectData(MensagensID.PCErrado);
            }
        }
        for (RestPaciente p : listaPacientes){
            visitaHomecareRepository.alterarPaciente(UtilSecurity.decryptId(p.getId()), UtilSecurity.decryptId(id));
            capeanteInternacaoRepository.alterarPaciente(UtilSecurity.decryptId(p.getId()), UtilSecurity.decryptId(id));
            internacaoRepository.alterarPaciente(UtilSecurity.decryptId(p.getId()), UtilSecurity.decryptId(id));
            deletar(p.getId());
        }
    }

    public RestPaciente novo(RestPaciente restPaciente) {
        Paciente paciente = pacienteRepository.validarCadastro(restPaciente.getNome(),restPaciente.getDataNascimento(),
                UtilSecurity.decryptId(restPaciente.getCliente().getId()));
        if(paciente != null){
            throw new ObjectAlreadyInBase(MensagensID.PC + restPaciente.getNome() + MensagensID.JaCadastrado);
        }
        
        paciente = pacienteRepository.save(restPaciente.restParaModel());
        
        return paciente.modelParaSimpleRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(RestPaciente restPaciente, String id) {
        if(!pacienteRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        restPaciente.setId(id);
        Paciente paciente = restPaciente.restParaModel();
//        paciente.setInternacoes((List<Internacao>) internacaoRepository.getInternacoes(paciente.getId()));
        pacienteRepository.save(paciente);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id) {
        Paciente paciente = pacienteRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR050));
        paciente.setStatus(new StatusPaciente(StatusPaciente.EXCLUIDO));
        pacienteRepository.save(paciente);
    }

    public Collection<RestPaciente> listaPacientesVisitaConcorrente(){
        JWTAuthentication usuarioLogado = RuleUtil.getUsuarioLogado();
        if(StringUtils.equals(usuarioLogado.getControleAcesso().getCategoria(), CategoriaGrupoUsuario.AU.name()))
		{
            Collection<Long> hospitais = new ArrayList<>();
            usuarioLogado.getControleAcesso().getVinculos().forEach(v -> {
                if(v.getHospitalId() != null){
                    hospitais.add(v.getHospitalId());
                }
            });
            Collection<Long> hospitaisId= hospitais.stream().distinct().collect(Collectors.toList());
            Collection<Paciente> pacientes = pacienteRepository.listaPacientesVisitaConcorrente(hospitaisId);
            return pacientes.stream().map(Paciente::modelParaRest).collect(Collectors.toList());
		}else {
            return listaSimples();
        }

    }

    @Override
    protected CrudRepository<Paciente, Long> getRepository() {
        return pacienteRepository;
    }
}
