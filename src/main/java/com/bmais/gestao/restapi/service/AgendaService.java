package com.bmais.gestao.restapi.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.model.TipoAgenda;
import com.bmais.gestao.restapi.repository.AgendaRepository;
import com.bmais.gestao.restapi.repository.TipoAgendaRepository;
import com.bmais.gestao.restapi.restmodel.RestAgenda;
import com.bmais.gestao.restapi.restmodel.RestAgendaPesquisa;
import com.bmais.gestao.restapi.restmodel.RestTipoAgenda;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final TipoAgendaRepository tipoAgendaRepository;

    public AgendaService(AgendaRepository agendaRepository, TipoAgendaRepository tipoAgendaRepository) {
        this.agendaRepository = agendaRepository;
		this.tipoAgendaRepository = tipoAgendaRepository;
    }

    public Collection<RestAgenda> lista(RestAgendaPesquisa params){
        Collection<Agenda> agendas = agendaRepository.lista(params);
        return agendas.stream()
        		.map(Agenda::modelParaRest)
        		.collect(Collectors.toList());
    }
    
    public Collection<RestTipoAgenda> listaTiposAgenda()
    {
    	Collection<TipoAgenda> tipos = tipoAgendaRepository.findAllByOrderByDescricaoAsc();
		return tipos.stream().map(TipoAgenda::modelParaRest).collect(Collectors.toList());
    }
    
    
    public Boolean auditorTemAgenda(String auditor, LocalDate data)
    {
    	return agendaRepository.existeAgendaParaAuditor(UtilSecurity.decryptId(auditor), data);
    }

    public void novo(RestAgenda restAgenda){
        agendaRepository.save(restAgenda.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(String id, RestAgenda restAgenda){
    	boolean existsById = agendaRepository.existsById(UtilSecurity.decryptId(id));
    	
    	if(!existsById)
    	{
    		 throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
    	}
    	
    	restAgenda.setId(id);
    	agendaRepository.save(restAgenda.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public void apagar(String id)
	{
    	boolean existsById = agendaRepository.existsById(UtilSecurity.decryptId(id));
    	
    	if(!existsById)
    	{
    		 throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
    	}
		
		agendaRepository.deleteById(UtilSecurity.decryptId(id));
	}
}
