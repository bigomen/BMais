package com.bmais.gestao.restapi.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.CapeanteInternacao;
import com.bmais.gestao.restapi.model.StatusCapeante;
import com.bmais.gestao.restapi.model.projections.CapeanteInternacaoProjection;
import com.bmais.gestao.restapi.repository.CapeanteInternacaoRepository;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacaoPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CapeanteInternacaoService extends com.bmais.gestao.restapi.service.Service<CapeanteInternacao, RestCapeanteInternacao>
{
	private final CapeanteInternacaoRepository capeanteInternacaoRepository;
	
	@Autowired
	public CapeanteInternacaoService(CapeanteInternacaoRepository capeanteInternacaoRepository)
	{
		super();
		this.capeanteInternacaoRepository = capeanteInternacaoRepository;
	}
	
	public Collection<RestCapeanteInternacao> lista(RestCapeanteInternacaoPesquisa params)
	{
		Collection<CapeanteInternacaoProjection> internacoes = capeanteInternacaoRepository.listaInternacoes(params);
		
		return internacoes.stream()
				.map(CapeanteInternacaoProjection::projectionToRest)
				.collect(Collectors.toList());
	}
	
	public RestCapeanteInternacao detalhar(String id)
	{
		Optional<CapeanteInternacao> internacao = capeanteInternacaoRepository.detalharInternacao(UtilSecurity.decryptId(id));

		RestCapeanteInternacao restCapeanteInternacao = internacao.orElseThrow(() -> new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado))
				.modelParaRest();
		restCapeanteInternacao.getProntuario().getPaciente().setCliente(internacao.get().getProntuario().getPaciente().getCliente().modelParaFullRest());
		restCapeanteInternacao.getProntuario().getPaciente().getCliente().setDadosBancarios(null);
		restCapeanteInternacao.getProntuario().getPaciente().getCliente().setEndereco(null);
		restCapeanteInternacao.getProntuario().getPaciente().getCliente().setHospitais(null);
		restCapeanteInternacao.getProntuario().getPaciente().getCliente().setContatos(null);
		return restCapeanteInternacao;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void novo(RestCapeanteInternacao restCapeanteInternacao)
	{
		capeanteInternacaoRepository.save(restCapeanteInternacao.restParaModel());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void atualizar(String id, RestCapeanteInternacao restCapeanteInternacao)
	{
		boolean existsById = capeanteInternacaoRepository.existsById(UtilSecurity.decryptId(id));
		
		if(!existsById)
		{
			throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
		}
		
		restCapeanteInternacao.setId(id);
		CapeanteInternacao internacao = restCapeanteInternacao.restParaModel();
		
		if(internacao.ehAlta())
		{
			internacao.setStatus(new StatusCapeante(StatusCapeante.INATIVO));
		}
		
		capeanteInternacaoRepository.save(internacao);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void excluir(String id)
	{
		boolean existsById = capeanteInternacaoRepository.existsById(UtilSecurity.decryptId(id));
		
		if(!existsById)
		{
			throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
		}
		
		capeanteInternacaoRepository.excluir(UtilSecurity.decryptId(id), StatusCapeante.EXCLUIDO);
	}
	
	@Override
	protected CrudRepository<CapeanteInternacao, Long> getRepository()
	{
		return capeanteInternacaoRepository;
	}
}
