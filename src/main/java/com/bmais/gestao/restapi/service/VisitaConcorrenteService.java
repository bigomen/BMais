package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.*;
import com.bmais.gestao.restapi.security.JWTAuthentication;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class VisitaConcorrenteService {

    private final VisitaConcorrenteRepository visitaConcorrenteRepository;
    private final EvolucaoVisitaRepository evolucaoVisitaRepository;
    private final AvaliacaoRelatorioRepository avalizacaoRelatorioRepository;
    private final TipoComponenteAutoCustoRepository tipoComponenteAutoCustoRepository;
    private final StatusVisitaRepository statusVisitaRepository;
	private final AcomodacaoRepository acomodacaoRepository;

	private final InternacaoService internacaoService;
	private final InternacaoRepository internacaoRepository;
	private final PacienteRepository pacienteRepository;

	private final ClienteRepository clienteRepository;

    public VisitaConcorrenteService(VisitaConcorrenteRepository visitaConcorrenteRepository, EvolucaoVisitaRepository evolucaoVisitaRepository,
									AvaliacaoRelatorioRepository avalizacaoRelatorioRepository, TipoComponenteAutoCustoRepository tipoComponenteAutoCustoRepository, StatusVisitaRepository statusVisitaRepository, AcomodacaoRepository acomodacaoRepository, InternacaoService internacaoService, InternacaoRepository internacaoRepository, PacienteRepository pacienteRepository, ClienteRepository clienteRepository) {
        this.visitaConcorrenteRepository = visitaConcorrenteRepository;
		this.evolucaoVisitaRepository = evolucaoVisitaRepository;
		this.avalizacaoRelatorioRepository = avalizacaoRelatorioRepository;
		this.tipoComponenteAutoCustoRepository = tipoComponenteAutoCustoRepository;
		this.statusVisitaRepository = statusVisitaRepository;
		this.acomodacaoRepository = acomodacaoRepository;
		this.internacaoService = internacaoService;
		this.internacaoRepository = internacaoRepository;
		this.pacienteRepository = pacienteRepository;
		this.clienteRepository = clienteRepository;
	}

    public Collection<RestVisita> lista(RestVisitaConcorrentePesquisa params){
//		JWTAuthentication usuarioLogado = RuleUtil.getUsuarioLogado();
//		if(StringUtils.equals(usuarioLogado.getControleAcesso().getCategoria(), CategoriaGrupoUsuario.AU.name()))
//		{
//			params.setVinculos(usuarioLogado.getControleAcesso().getVinculos());
//		}
        Collection<Visita> visitas = visitaConcorrenteRepository.lista(params);
        return visitas.stream().map(Visita::modelParaRest).collect(Collectors.toList());
    }

    public RestVisita detalhes(String id){
        if(!visitaConcorrenteRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Visita visita = visitaConcorrenteRepository.detalhes(UtilSecurity.decryptId(id));
		Collection<RestEvolucaoVisita> evolucao = evolucao(id);
		RestVisita restVisita = visita.modelParaRest();
		if(evolucao.size() != 0){
			restVisita.setEvolucao(evolucao.stream().findFirst().get().getDescricao());
		}
		restVisita.setProrrogacao(clienteRepository.getProrrogacaoCliente(visita.getInternacao().getPaciente().getCliente().getId()));
        return restVisita;
    }
    
    public Collection<RestAvaliacaoRelatorio> listarItensAvaliacaoRelatorio()
    {
		Collection<AvaliacaoRelatorio> all = avalizacaoRelatorioRepository.findAll(Sort.by(Sort.Direction.ASC, AvaliacaoRelatorio_.DESCRICAO));

		return all.stream()
				.peek(it -> it.setVisitas(null))
				.map(AvaliacaoRelatorio::modelParaRest)
				.sorted()
				.collect(Collectors.toList());
    }

	private Internacao validaInternacao(Internacao i){
		Optional<Internacao> internacao = internacaoRepository.findById(i.getId());
		if(internacao.isPresent()){
			if (i.getObito().booleanValue() || i.getDataHoraAlta() != null) {
				i.setStatus(new StatusInternacao(StatusInternacao.INATIVO));
				internacao.get().setDataHoraAlta(i.getDataHoraAlta());
			} else if (!i.getObito().booleanValue()  && i.getDataHoraAlta() == null) {
				i.setStatus(new StatusInternacao(StatusInternacao.ATIVO));
			}
			internacao.get().setStatus(i.getStatus());
			internacao.get().setObito(i.getObito());
			internacao.get().setDiasLiberados(i.getDiasLiberados());
			return internacao.get();
		}else {
			throw new ObjectNotFoundException("Internação não encontrada");
		}

	}

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestVisita restVisita){
		JWTAuthentication usuarioLogado = RuleUtil.getUsuarioLogado();
		if(StringUtils.equals(usuarioLogado.getControleAcesso().getCategoria(), CategoriaGrupoUsuario.AU.name())) {
			Long auditorId = usuarioLogado.getControleAcesso().getAuditorId();
			if(auditorId != null){
				if(Objects.isNull(restVisita.getAuditor())){
					Auditor auditor = new Auditor();
					auditor.setId(auditorId);
					restVisita.setAuditor(auditor.modelParaRest());
				}else {
					restVisita.getAuditor().setId(UtilSecurity.encryptId(auditorId));
				}
			}
		}

		Visita visita = restVisita.restParaModel();
		visita.setInternacao(validaInternacao(visita.getInternacao()));
        visita = visitaConcorrenteRepository.save(visita);
        
        if(StringUtils.isNoneBlank(restVisita.getEvolucao()))
        {
			EvolucaoVisita evolucaoVisita = evolucaoVisita(visita, restVisita.getEvolucao());
			evolucaoVisitaRepository.save(evolucaoVisita);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestVisita restVisita, String id){
        if(!visitaConcorrenteRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        restVisita.setId(id);
		Visita visita = restVisita.restParaModel();
		visita.setInternacao(validaInternacao(visita.getInternacao()));
        visitaConcorrenteRepository.save(visita);

		EvolucaoVisita evolucao = evolucaoVisitaRepository.findByVisita(visita);
        if(StringUtils.isBlank(restVisita.getEvolucao()) && Objects.nonNull(evolucao) )
        {
			evolucaoVisitaRepository.delete(evolucao);
        } else if (StringUtils.isNoneBlank(restVisita.getEvolucao()) && Objects.isNull(evolucao)) {
			EvolucaoVisita evolucaoVisita = evolucaoVisita(visita, restVisita.getEvolucao());
			evolucaoVisitaRepository.save(evolucaoVisita);
		}else if(StringUtils.isNoneBlank(restVisita.getEvolucao()) && Objects.nonNull(evolucao)){
			evolucao.setDescricao(restVisita.getEvolucao());
			evolucaoVisitaRepository.save(evolucao);
		}
	}
    
    private EvolucaoVisita evolucaoVisita(Visita visita, String texto)
    {
		EvolucaoVisita evolucao = new EvolucaoVisita();
		evolucao.setVisita(visita);
		evolucao.setDescricao(texto);
		return evolucao;
    }

	public Collection<RestEvolucaoVisita> evolucao(String id) {
		Collection<EvolucaoVisita> evolucoes = evolucaoVisitaRepository.findByVisitaIdOrderByDataEvolucaoDesc(UtilSecurity.decryptId(id));
		return evolucoes.stream()
				.map(EvolucaoVisita::modelParaRest)
				.collect(Collectors.toList());
	}

	public Collection<RestEvolucaoVisita> pesquisarEvolucoes(String id){
		Long idInternacao = visitaConcorrenteRepository.getInternacaoId(UtilSecurity.decryptId(id));
		Collection<EvolucaoVisita> evolucoes = evolucaoVisitaRepository.evolucoesInternacao(idInternacao);
		return evolucoes.stream().map(EvolucaoVisita::modelParaRest).collect(Collectors.toList());
	}

	public Collection<RestTipoComponente> listarTiposComponentes() {
		Collection<TipoComponente> tiposDeComponentes = tipoComponenteAutoCustoRepository.findAll(Sort.by(Sort.Direction.ASC, TipoComponente_.DESCRICAO));

		return tiposDeComponentes.stream()
				.map(TipoComponente::modelParaRest)
				.collect(Collectors.toList());
	}

	public Collection<RestStatusVisita> listaStatus() {
		Collection<StatusVisita> findAll = statusVisitaRepository.findAll();

		return findAll.stream().map(StatusVisita::modelParaRest).collect(Collectors.toList());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void aprovar(String id) {
		Optional<Visita> findById = visitaConcorrenteRepository.findById(UtilSecurity.decryptId(id));

		Visita visita = findById.orElseThrow(()-> new RuntimeException("Visita Não localizada"));

		visita.setStatus(new StatusVisita(StatusVisita.APROVADO));
		visitaConcorrenteRepository.save(visita);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void recusar(String id) {
		Optional<Visita> findById = visitaConcorrenteRepository.findById(UtilSecurity.decryptId(id));

		Visita visita = findById.orElseThrow(()-> new RuntimeException("Visita Não localizada"));

		visita.setStatus(new StatusVisita(StatusVisita.RECUSADO));
		visitaConcorrenteRepository.save(visita);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void excluir(String id) {
		Long visitaId = UtilSecurity.decryptId(id);
		if(!visitaConcorrenteRepository.existsById(visitaId)){
			throw new ObjectNotFoundException("Visita não encontrada");
		}
		Long internacaoId = visitaConcorrenteRepository.getInternacaoId(visitaId);
		if(visitaConcorrenteRepository.validarExclusao(internacaoId, visitaId)){
			throw new IncorrectData("A exclusão de visitas de uma internação deve ser feita por ordem de inserção no sistema");
		}
		evolucaoVisitaRepository.deleteByVisitaId(visitaId);
		visitaConcorrenteRepository.deleteById(visitaId);
	}

	public Collection<RestCID> pesquisarUltimoCid(String paciente) {
		Collection<CID> cids = visitaConcorrenteRepository.pesquisarUltimoCID(paciente);
		return cids.stream().map(CID::modelParaRest).collect(Collectors.toList());
	}

	public Collection<RestAcomodacao> acomodacoes(){
		Collection<Acomodacao> acomodacaos = (Collection<Acomodacao>) acomodacaoRepository.findAll();
		return acomodacaos.stream().map(Acomodacao::modelParaRest).collect(Collectors.toList());
	}
}
