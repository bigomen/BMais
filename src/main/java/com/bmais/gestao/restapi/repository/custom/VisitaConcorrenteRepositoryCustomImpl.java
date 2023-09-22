package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestVisitaConcorrentePesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@org.springframework.stereotype.Repository
public class VisitaConcorrenteRepositoryCustomImpl extends Repository<Visita> implements VisitaConcorrenteRepositoryCustom{

    private final AuditorRepository auditorRepository;
    private final ComponenteAltoCustoRepository componenteAltoCustoRepository;
    private final CurativoRepository curativoRepository;
    private final AvaliacaoRelatorioRepository avaliacaoRelatorioRepository;
    private final InternacaoRepository internacaoRepository;
    private final AcomodacaoVisitaRepository acomodacaoVisitaRepository;

    @Autowired
    public VisitaConcorrenteRepositoryCustomImpl(
            AuditorRepository auditorRepository,
            ComponenteAltoCustoRepository componenteAltoCustoRepository,
            CurativoRepository curativoRepository,
            AvaliacaoRelatorioRepository avaliacaoRelatorioRepository, InternacaoRepository internacaoRepository, AcomodacaoVisitaRepository acomodacaoVisitaRepository)
	{
		super();
		this.auditorRepository = auditorRepository;
		this.componenteAltoCustoRepository = componenteAltoCustoRepository;
		this.curativoRepository = curativoRepository;
		this.avaliacaoRelatorioRepository = avaliacaoRelatorioRepository;
		this.internacaoRepository = internacaoRepository;
        this.acomodacaoVisitaRepository = acomodacaoVisitaRepository;
    }

	@Override
    public Collection<Visita> lista(RestVisitaConcorrentePesquisa params) {
        CriteriaQuery<Visita> criteria = super.getCriteria();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Root<Visita> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Join<Visita, Internacao> joinInternacao = root.join(Visita_.INTERNACAO);
        Join<Internacao, Paciente> joinPaciente = joinInternacao.join(Internacao_.PACIENTE);
        Join<Paciente, Cliente> joinCliente = joinPaciente.join(Paciente_.CLIENTE);
        Join<Internacao, Hospital> joinHospital = joinInternacao.join(Internacao_.HOSPITAL, JoinType.INNER);
        Join<Internacao, StatusInternacao> joinStatusInternacao = joinInternacao.join(Internacao_.STATUS, JoinType.INNER);
        Join<Visita, StatusVisita> joinStatusVisita = root.join(Visita_.STATUS, JoinType.INNER);
        Join<Visita, Auditor> joinAuditor = root.join(Visita_.AUDITOR, JoinType.INNER);

        Path<Long> idVisita = root.get(Visita_.ID);
        Path<LocalDate> dataVisita = root.get(Visita_.DATA);
        Path<String> obs = root.get(Visita_.OBSERVACAO);
        Path<String> statusVisita = joinStatusVisita.get(StatusVisita_.DESCRICAO);
        
        Path<Long> idInternacao = joinInternacao.get(Internacao_.ID);
        Path<LocalDateTime> dataInternacao = joinInternacao.get(Internacao_.DATA_HORA);
        Path<LocalDateTime> dataAlta = joinInternacao.get(Internacao_.DATA_HORA_ALTA);
        Path<String> senhaConvenio = joinInternacao.get(Internacao_.SENHA);
        Path<String> nomeHospital = joinHospital.get(Hospital_.RAZAO_SOCIAL);
        Path<Long> idStatusInternacao = joinStatusInternacao.get(StatusInternacao_.ID);
        Path<String> descStatusInternacao = joinStatusInternacao.get(StatusInternacao_.DESCRICAO);
        
        Path<Long> idCliente = joinCliente.get(Cliente_.ID);
        Path<String> razaoCliente = joinCliente.get(Cliente_.RAZAO_SOCIAL);

        Path<Long> idPaciente = joinPaciente.get(Paciente_.ID);
        Path<String> nomePaciente = joinPaciente.get(Paciente_.NOME);
        Path<Date> dataNascimento = joinPaciente.get(Paciente_.DATA_NASCIMENTO);
        Path<String> matriculaPaciente = joinPaciente.get(Paciente_.MATRICULA);
        
        Path<Long> idAuditor = joinAuditor.get(Auditor_.ID);
        Path<String> nomeAuditor = joinAuditor.get(Auditor_.NOME);
        
        criteria.multiselect(idVisita, dataVisita, obs, statusVisita, 
        		idInternacao, dataInternacao, dataAlta, senhaConvenio, idStatusInternacao, descStatusInternacao,
        		nomeHospital, idPaciente, nomePaciente, dataNascimento, matriculaPaciente, idCliente, razaoCliente, idAuditor, nomeAuditor);

        if(StringUtils.isNotBlank(params.getPaciente())){
            Predicate equal = builder().equal(joinPaciente.get(Paciente_.ID), UtilSecurity.decryptId(params.getPaciente()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getInternacao())){
            Predicate equal = builder().equal(joinInternacao.get(Internacao_.ID), UtilSecurity.decryptId(params.getInternacao()));
            conjunction = builder().and(conjunction, equal);
            criteria.orderBy(cb.asc(idVisita));
        }else {
            criteria.orderBy(builder().asc(nomePaciente));
        }

        if(StringUtils.isNotBlank(params.getMatriculaPaciente())){
            Predicate like = builder().like(builder().upper(joinPaciente.get(Paciente_.MATRICULA)), like(params.getPaciente().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if (StringUtils.isNotBlank(params.getCliente())){
            Predicate equal = builder().equal(joinCliente.get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equal);
        }
        if (StringUtils.isNotBlank(params.getHospital())){
            Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), UtilSecurity.decryptId(params.getHospital()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getSenhaConvenio())){
            Predicate like = builder().like(builder().upper(joinInternacao.get(Internacao_.SENHA)), like(params.getSenhaConvenio().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(params.getPeriodoInicial() != null && params.getPeriodoFinal() != null){
            Predicate between = builder().between(root.get(Visita_.DATA), params.getPeriodoInicial(), params.getPeriodoFinal());
            conjunction = builder().and(conjunction, between);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Predicate equal = builder().equal(joinStatusVisita.get(StatusVisita_.ID), Long.parseLong(params.getStatus()));
            conjunction = builder().and(conjunction, equal);
        }else {
            Predicate notEqual = builder().notEqual(joinStatusVisita.get(StatusVisita_.ID), StatusVisita.EXCLUIDO);
            conjunction = builder().and(conjunction, notEqual);
        }

//        if(params.getVinculos() != null){
//
//            params.getVinculos().forEach(v -> {
//                if(v.getHospitalId() != null){
//                    Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), v.getHospitalId());
//                    conjunction = builder().or(conjunction, equal);
//                }
//            });
//        }
        criteria.where(conjunction);
        TypedQuery<Visita> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Visita detalhes(Long id){
        CriteriaQuery<Visita> criteria = super.getCriteria();
        Root<Visita> root = criteria.from(getClazz());
        root.fetch(Visita_.CID_PRINCIPAL, JoinType.INNER);
        root.fetch(Visita_.CID_SECUNDARIO, JoinType.LEFT);
        root.fetch(Visita_.PROCEDIMENTO_NEGADO, JoinType.LEFT);
        root.fetch(Visita_.TROCA_PROCEDIMENTO_DE, JoinType.LEFT);
        root.fetch(Visita_.TROCA_PROCEDIMENTO_PARA, JoinType.LEFT);
        root.fetch(Visita_.GLOSA_DIARIA_ACOMODACAO, JoinType.LEFT);
        root.fetch(Visita_.TROCA_DIARIA_DE, JoinType.LEFT);
        root.fetch(Visita_.TROCA_DIARIA_PARA, JoinType.LEFT);

        criteria.where(builder().equal(root.get(Visita_.ID), id));

        TypedQuery<Visita> typedQuery = entityManager.createQuery(criteria);
        Visita visita = typedQuery.getSingleResult();
        Internacao internacao = internacaoRepository.detalhes(visita.getInternacao().getId());
        
        visita.setInternacao(internacao);

        visita.setAuditor(new HashSet(auditorRepository.auditoresVisita(visita.getId())));
        visita.setComponentesAltoCusto(componenteAltoCustoRepository.componentesVisita(visita.getId()));
        visita.setCurativos(curativoRepository.curativosVisita(visita.getId()));
        visita.setAcomodacoes(acomodacaoVisitaRepository.getAcomodacoes(visita.getId()));

        Iterable<AvaliacaoRelatorio> all = avaliacaoRelatorioRepository.findAll();
        List<AvaliacaoRelatorio> avaliacoes = avaliacaoRelatorioRepository.pesquisarAvaliacoes(visita.getId());

        Set<AvaliacaoRelatorio> avas = new TreeSet<AvaliacaoRelatorio>();

        avaliacoes.forEach(avas :: add);
        all.forEach( av -> {
        	av.setVisitas(null);
        	avas.add(av);
        });

        visita.setAvaliacaoRelatorio(avas);
//        visita.getAvaliacaoRelatorio().setVisita(null);
        return visita;
    }

    @Override
    public Class<Visita> getClazz() {
        return Visita.class;
    }

	@Override
	public Collection<CID> pesquisarUltimoCID(String paciente)
	{
		String sql = "SELECT "
					+ 	"V.CID_ID_PRINCIPAL AS ID_PRINCIPAL, "
					+ 	"C.CID_DESCRICAO AS DESC_PRINCIPAL,"
					+   "V.CID_ID_SECUNDARIO AS ID_SECUNDARIO, "
					+   "C2.CID_DESCRICAO AS DESC_SECUNDARIO "
					+ "FROM VISITA V INNER JOIN CID C ON C.CID_ID = V.CID_ID_PRINCIPAL INNER JOIN CID C2 ON C2.CID_ID = V.CID_ID_SECUNDARIO "  
					+ "WHERE V.VIS_ID IN( SELECT MAX(VIS_ID) FROM VISITA V2 INNER JOIN INTERNACAO I ON V2.INT_ID = I.INT_ID WHERE I.PAC_ID = :PACIENTE)";
		
		Query query = super.entityManager.createNativeQuery(sql);
		query.setParameter("PACIENTE", UtilSecurity.decryptId(paciente));
		List<Object[]> resultList = query.getResultList();
		
		List<CID> cids = new ArrayList<>();
		
		resultList.forEach(r -> {
			CID cidPrincipal = new CID(((Integer)r[0]).longValue(), (String)r[1]);
			cids.add(cidPrincipal);

			if(r[2] != null)
			{
				CID cidSecundario = new CID(((Integer)r[2]).longValue(), (String)r[3]);
				cids.add(cidSecundario);
			}
		});
		
		
		return cids;
	}
}
