package com.bmais.gestao.restapi.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.mapper.EscalaMapper;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.model.Vinculo;
import com.bmais.gestao.restapi.model.projections.EscalaProjection;
import com.bmais.gestao.restapi.repository.CoberturaRepository;
import com.bmais.gestao.restapi.repository.VinculosRepository;
import com.bmais.gestao.restapi.restmodel.RestAuditor;
import com.bmais.gestao.restapi.restmodel.RestCobertura;
import com.bmais.gestao.restapi.restmodel.RestEscala;
import com.bmais.gestao.restapi.restmodel.RestEscalaPesquisa;
import com.bmais.gestao.restapi.utility.UtilData;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CoberturaService {

    private final CoberturaRepository coberturaRepository;
    private final VinculosRepository vinculosRepository;

    public CoberturaService(CoberturaRepository coberturaRepository, VinculosRepository vinculosRepository) 
    {
        this.coberturaRepository = coberturaRepository;
        this.vinculosRepository = vinculosRepository;
    }

    public Collection<RestEscala> listaCoberturas(RestEscalaPesquisa params)
    {
    	Collection<EscalaProjection> escalaveis = vinculosRepository.pesquisarEscalas(params);
    	Collection<EscalaProjection> coberturas = vinculosRepository.pesquisarCoberturas(params);
    	
    	escalaveis.addAll(coberturas);
    	
    	AtomicLong fakeId = new AtomicLong(1L);
    	
    	return escalaveis.stream()
        		.map(EscalaMapper.INSTANCE::convertToRest)
        		.sorted(new Comparator<RestEscala>()
				{
					@Override
					public int compare(RestEscala o1, RestEscala o2)
					{
						if(o1.getAuditor().getNome().equals(o2.getAuditor().getNome()))
						{
							if(o1.getCobertura() != null && o2.getCobertura() != null)
							{
								return o1.getCobertura().getPeriodoInicio().compareTo(o2.getCobertura().getPeriodoFim());
							}
						}
						
						return o1.getAuditor().getNome().compareTo(o2.getAuditor().getNome());
					}
				})
        		.peek(esc -> esc.setFakeId(fakeId.getAndAdd(1)))
        		.collect(Collectors.toList());
    }

    public Collection<RestEscala> listaEscala(RestEscalaPesquisa params)
    {
        Collection<EscalaProjection> escalas = vinculosRepository.pesquisarEscalas(params);
        
        escalas.forEach(esc -> {
        	Optional<Cobertura> cobertura = coberturaRepository.pesquisarCobertura(esc.getIdEscala());
        	cobertura.ifPresent(c -> esc.setCobertura(c));
        });
        
        return escalas.stream()
        		.map(EscalaMapper.INSTANCE::convertToRest)
        		.collect(Collectors.toList());
    }

    public void novo(Collection<RestCobertura> restCoberturas)
    {
    	List<Cobertura> coberturas = restCoberturas.stream()
    			.map(RestCobertura::restParaModel)
    			.collect(Collectors.toList());
    	
    	coberturas.forEach(this::validarcobertura);
    	
        coberturaRepository.saveAll(coberturas);
    }

	private void validarcobertura(Cobertura cobertura)
	{
		if(cobertura.getId() != null)
		{
			coberturaRepository.delete(cobertura);
		}
		
		Boolean existeCoberturaAuditor = coberturaRepository.existeCoberturaAuditor(cobertura.getVinculo().getId(), cobertura.getPeriodoInicio(), cobertura.getPeriodoFim());
		
		if(existeCoberturaAuditor)
		{
			Optional<Vinculo> escala = vinculosRepository.pesquisarVinculoauditor(cobertura.getVinculo().getId());
			
			
			String message = String.format("Já existe cobertura cadastrada para o auditor %s no período de %s a %s", 
					escala.get().getAuditor().getNome(), 
					UtilData.formataData(cobertura.getPeriodoInicio(), "dd/MM/yyyy"),
					UtilData.formataData(cobertura.getPeriodoFim(), "dd/MM/yyyy"));
			
			throw new RuntimeException(message);
		}
	}
}
