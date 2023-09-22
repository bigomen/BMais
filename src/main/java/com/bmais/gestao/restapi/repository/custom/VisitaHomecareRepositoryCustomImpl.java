package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomecarePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class VisitaHomecareRepositoryCustomImpl extends Repository<VisitaHomeCare> implements VisitaHomecareRepositoryCustom{

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ClienteRepository clienteRepository;
    private final HospitalRepository hospitalRepository;
    private final EnderecoRepository enderecoRepository;
    private final AuditorRepository auditorRepository;
    private final SintomaQuadroClinicoRepository sintomaQuadroClinicoRepository;
    private final DiagnosticoAuditoriaVisitaHCRepository diagnosticoAuditoriaVisitaHCRepository;
    private final FeridasAuditoriaVisitaHCRepository feridasAuditoriaVisitaHCRepository;
    private final StomiaAuditoriaVisitaHCRepository stomiaAuditoriaVisitaHCRepository;
    private final ServicoPrestadoAuditoriaVisitaHCRepository servicoPrestadoAuditoriaVisitaHCRepository;
    private final MaterialAuditoriaVisitaHCRepository materialAuditoriaVisitaHCRepository;
    private final MedicamentoAuditoriaVisitaHCRepository medicamentoAuditoriaVisitaHCRepository;
    private final EquipamentoAuditoriaVisitaHCRepository equipamentoAuditoriaVisitaHCRepository;
    private final ConclusaoAuditoriaHCRepository conclusaoAuditoriaHCRepository;

    public VisitaHomecareRepositoryCustomImpl(UsuarioRepository usuarioRepository, PacienteRepository pacienteRepository, ClienteRepository clienteRepository, HospitalRepository hospitalRepository, EnderecoRepository enderecoRepository, AuditorRepository auditorRepository, SintomaQuadroClinicoRepository sintomaQuadroClinicoRepository, DiagnosticoAuditoriaVisitaHCRepository diagnosticoAuditoriaVisitaHCRepository, FeridasAuditoriaVisitaHCRepository feridasAuditoriaVisitaHCRepository, StomiaAuditoriaVisitaHCRepository stomiaAuditoriaVisitaHCRepository, ServicoPrestadoAuditoriaVisitaHCRepository servicoPrestadoAuditoriaVisitaHCRepository, MaterialAuditoriaVisitaHCRepository materialAuditoriaVisitaHCRepository, MedicamentoAuditoriaVisitaHCRepository medicamentoAuditoriaVisitaHCRepository, EquipamentoAuditoriaVisitaHCRepository equipamentoAuditoriaVisitaHCRepository, ConclusaoAuditoriaHCRepository conclusaoAuditoriaHCRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.clienteRepository = clienteRepository;
        this.hospitalRepository = hospitalRepository;
        this.enderecoRepository = enderecoRepository;
        this.auditorRepository = auditorRepository;
        this.sintomaQuadroClinicoRepository = sintomaQuadroClinicoRepository;
        this.diagnosticoAuditoriaVisitaHCRepository = diagnosticoAuditoriaVisitaHCRepository;
        this.feridasAuditoriaVisitaHCRepository = feridasAuditoriaVisitaHCRepository;
        this.stomiaAuditoriaVisitaHCRepository = stomiaAuditoriaVisitaHCRepository;
        this.servicoPrestadoAuditoriaVisitaHCRepository = servicoPrestadoAuditoriaVisitaHCRepository;
        this.materialAuditoriaVisitaHCRepository = materialAuditoriaVisitaHCRepository;
        this.medicamentoAuditoriaVisitaHCRepository = medicamentoAuditoriaVisitaHCRepository;
        this.equipamentoAuditoriaVisitaHCRepository = equipamentoAuditoriaVisitaHCRepository;
        this.conclusaoAuditoriaHCRepository = conclusaoAuditoriaHCRepository;
    }


    @Override
    public Collection<VisitaHomeCare> lista(RestVisitaHomecarePesquisa params){
        CriteriaQuery<VisitaHomeCare> criteria = super.getCriteria();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Root<VisitaHomeCare> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Join<VisitaHomeCare, ProntuarioVisitaHomeCare> joinProntuario = root.join(VisitaHomeCare_.PRONTUARIO);
        Join<VisitaHomeCare, StatusVisita> joinStatus = root.join(VisitaHomeCare_.STATUS, JoinType.INNER);
        Join<ProntuarioVisitaHomeCare, Paciente> joinProntuarioPaciente = joinProntuario.join(ProntuarioVisitaHomeCare_.PACIENTE);
        Join<Paciente, Cliente> joinPacienteCliente = joinProntuarioPaciente.join(Paciente_.CLIENTE);
        Join<VisitaHomeCare, AuditoriaVisitaHomeCare> joinAuditoria = root.join(VisitaHomeCare_.AUDITORIA);
        Join<AuditoriaVisitaHomeCare, Auditor> joinAuditor = joinAuditoria.join(AuditoriaVisitaHomeCare_.AUDITOR);

        Path<Long> idVisita = root.get(VisitaHomeCare_.ID);
        Path<Long> numeroVisita = root.get(VisitaHomeCare_.NUMERO_VISITA);
        Path<Long> idProntuario = joinProntuario.get(ProntuarioVisitaHomeCare_.ID);
        Path<String> nomePaciente = joinProntuarioPaciente.get(Paciente_.NOME);
        Path<String> matriculaPaciente = joinProntuarioPaciente.get(Paciente_.MATRICULA);
        Path<String> razaoCliente = joinPacienteCliente.get(Cliente_.RAZAO_SOCIAL);
        Path<String> hospital = joinProntuario.get(ProntuarioVisitaHomeCare_.HOSPITAL);
        Path<String> status = joinStatus.get(StatusVisita_.DESCRICAO);
        Path<Date> dataVisita = joinAuditoria.get(AuditoriaVisitaHomeCare_.DATA_AUDITORIA);
        Path<Long> idAuditor = joinAuditor.get(Auditor_.ID);
        Path<String> nomeAuditor = joinAuditor.get(Auditor_.NOME);
        Path<String> observacao = joinAuditoria.get(AuditoriaVisitaHomeCare_.OBSERVACAO);


        criteria.multiselect(idVisita, numeroVisita, idProntuario, nomePaciente, matriculaPaciente, razaoCliente, hospital,
                status, dataVisita, idAuditor, nomeAuditor, observacao);

        if(StringUtils.isNotBlank(params.getProntuario())){
            Predicate equal = builder().equal(joinProntuario.get(ProntuarioVisitaHomeCare_.ID), UtilSecurity.decryptId(params.getProntuario()));
            conjunction = builder().and(conjunction, equal);
            criteria.orderBy(cb.asc(idVisita));
        }else {
            criteria.orderBy(builder().asc(nomePaciente));
        }

        if(StringUtils.isNotBlank(params.getPaciente())){
            Predicate equal = builder().equal(joinProntuarioPaciente.get(Paciente_.ID), UtilSecurity.decryptId(params.getPaciente()));
            conjunction = builder().and(conjunction, equal);
        }
        
        if(StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinPacienteCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equal);
        }
        
        if(StringUtils.isNotBlank(params.getHospital())){
//            Predicate equal = builder().equal(joinProntuarioHospital.get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
//            conjunction = builder().and(conjunction, equal);
            Predicate like = builder().like(builder().upper(hospital), params.getHospital().toUpperCase());
            conjunction = builder().and(conjunction, like);
        }
        
        if(StringUtils.isNotBlank(params.getMatriculaPaciente())){
        	Predicate equal = builder().equal(joinProntuarioPaciente.get(Paciente_.MATRICULA), params.getMatriculaPaciente());
            conjunction = builder().and(conjunction, equal);
        }
        
        if(StringUtils.isNotBlank(params.getStatus())){
            Predicate equal = builder().equal(joinStatus.get(StatusVisita_.ID), params.getStatus());
            conjunction = builder().and(conjunction, equal);
        }else {
            Predicate notEqual = builder().notEqual(joinStatus.get(StatusVisita_.ID), StatusVisita.EXCLUIDO);
            conjunction = builder().and(conjunction, notEqual);
        }

        criteria.where(conjunction);
        TypedQuery<VisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    public VisitaHomeCare detalhes(Long id){
        CriteriaQuery<VisitaHomeCare> criteria = super.getCriteria();
        Root<VisitaHomeCare> root = criteria.from(getClazz());
        root.fetch(VisitaHomeCare_.PRONTUARIO, JoinType.INNER);
        root.fetch(VisitaHomeCare_.STATUS, JoinType.INNER);
        root.fetch(VisitaHomeCare_.USUARIO, JoinType.INNER);
        criteria.where(builder().equal(root.get(VisitaHomeCare_.ID), id));
        TypedQuery<VisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
        VisitaHomeCare visita = typedQuery.getSingleResult();

        visita.setUsuario(usuarioRepository.usuarioVisita(visita.getUsuario().getId()));
        
        visita.getProntuario().setPaciente(pacienteRepository.detalhes(visita.getProntuario().getPaciente().getId()));
        visita.getProntuario().setEndereco(enderecoRepository.findById(visita.getProntuario().getEndereco().getId()).get());
//        visita.getProntuario().setCliente(clienteRepository.clientesPeloId(visita.getProntuario().getCliente().getId()));
//        visita.getAuditoria().setAuditor(auditorRepository.auditorAuditoria(visita.getAuditoria().getAuditor().getId()));
//        visita.getAuditoria().setSintomas(sintomaQuadroClinicoRepository.sintomasAuditoria(visita.getId()));
//        visita.getAuditoria().setDiagnosticos(diagnosticoAuditoriaVisitaHCRepository.diagnosticosAuditoria(visita));
//        visita.getAuditoria().setFeridas(feridasAuditoriaVisitaHCRepository.feridasAuditoria(visita));
//        visita.getAuditoria().setStomias(stomiaAuditoriaVisitaHCRepository.stomiasAuditoria(visita));
//        visita.getAuditoria().setServicosPrestados(servicoPrestadoAuditoriaVisitaHCRepository.servicosAuditoria(visita));
//        visita.getAuditoria().setMateriais(materialAuditoriaVisitaHCRepository.materiaisAuditoria(visita));
//        visita.getAuditoria().setMedicamentos(medicamentoAuditoriaVisitaHCRepository.medicamentosAuditoria(visita));
//        visita.getAuditoria().setEquipamentos(equipamentoAuditoriaVisitaHCRepository.equipamentosAuditoria(visita));
//        visita.getAuditoria().setConclusoes(conclusaoAuditoriaHCRepository.conclusoesAuditoria(visita.getId()));
        return visita;
    }

    public Long numeroVisita(){
        return Long.parseLong(entityManager.createNativeQuery("select nextval ('seq_numero_visita')")
                .getSingleResult().toString());
    }

    @Override
    public Class<VisitaHomeCare> getClazz() {
        return VisitaHomeCare.class;
    }


	@Override
	public Optional<VisitaHomeCare> pesquisarUltimaVisitaProntuario(
			ProntuarioVisitaHomeCare prontuario)
	{
		CriteriaQuery<VisitaHomeCare> criteria = super.getCriteria();
	    Root<VisitaHomeCare> root = criteria.from(getClazz());
	    
	    Subquery<Long> sub = criteria.subquery(Long.class);
	    Root<VisitaHomeCare> rootSub = sub.from(getClazz());
	    Expression<Long> ultimaVisita = builder().max(rootSub.get(VisitaHomeCare_.ID));
	    Predicate equalProntuario = builder().equal(rootSub.get(VisitaHomeCare_.PRONTUARIO), prontuario);
	    sub.where(equalProntuario);
	    sub.select(ultimaVisita);
	    
	    Predicate equalSub = builder().equal(root.get(VisitaHomeCare_.ID), sub);
	    criteria.where(equalSub);
	    
	    TypedQuery<VisitaHomeCare> typedQuery = entityManager.createQuery(criteria);
	    
	    try
	    {
	    	return Optional.of(typedQuery.getSingleResult());
	    }catch (NoResultException e) {
			return Optional.empty();
		}
	}
}
