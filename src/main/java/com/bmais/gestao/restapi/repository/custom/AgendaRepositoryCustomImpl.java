package com.bmais.gestao.restapi.repository.custom;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.model.Agenda_;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Auditor_;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Cliente_;
import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.model.Colaborador_;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.Hospital_;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.Servico_;
import com.bmais.gestao.restapi.model.TipoAgenda;
import com.bmais.gestao.restapi.model.TipoAgenda_;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.model.Usuario_;
import com.bmais.gestao.restapi.model.Vinculo;
import com.bmais.gestao.restapi.model.Vinculo_;
import com.bmais.gestao.restapi.model.enums.Periodo;
import com.bmais.gestao.restapi.restmodel.RestAgendaPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Repository
public class AgendaRepositoryCustomImpl extends com.bmais.gestao.restapi.repository.custom.Repository<Agenda> implements AgendaRepositoryCustom {

    @Override
    public Collection<Agenda> lista(RestAgendaPesquisa params){
        CriteriaQuery<Agenda> criteria = super.getCriteria();
        Root<Agenda> root = criteria.from(getClazz());

        Join<Agenda, TipoAgenda> joinTipoAgenda = root.join(Agenda_.TIPO, JoinType.INNER);
        Join<Agenda, Vinculo> joinVinculoMedico = root.join(Agenda_.MEDICO, JoinType.LEFT);
        Join<Agenda, Vinculo> joinVinculoEnfermeiro = root.join(Agenda_.ENFERMEIRO, JoinType.LEFT);
        Join<Agenda, Usuario> joinUsuarioEdicao = root.join(Agenda_.USUARIO_EDICAO, JoinType.LEFT);
        Join<Agenda, Usuario> joinUsuarioInclusao = root.join(Agenda_.USUARIO_INCLUSAO, JoinType.INNER);
        
        Join<Vinculo, Cliente> joinClienteMedico = joinVinculoMedico.join(Vinculo_.CLIENTE, JoinType.LEFT);
        Join<Vinculo, Hospital> joinHospitalMedico = joinVinculoMedico.join(Vinculo_.HOSPITAL, JoinType.LEFT);
        Join<Vinculo, Servico> joinServicoMedico = joinVinculoMedico.join(Vinculo_.SERVICO, JoinType.LEFT);
        Join<Vinculo, Auditor> joinMedico = joinVinculoMedico.join(Vinculo_.AUDITOR, JoinType.LEFT);
        
        Join<Vinculo, Cliente> joinClienteEnfermeiro = joinVinculoEnfermeiro.join(Vinculo_.CLIENTE, JoinType.LEFT);
        Join<Vinculo, Hospital> joinHospitalEnfermeiro = joinVinculoEnfermeiro.join(Vinculo_.HOSPITAL, JoinType.LEFT);
        Join<Vinculo, Servico> joinServicoEnfermeiro = joinVinculoEnfermeiro.join(Vinculo_.SERVICO, JoinType.LEFT);
        Join<Vinculo, Auditor> joinEnfermeiro = joinVinculoEnfermeiro.join(Vinculo_.AUDITOR, JoinType.LEFT);

        Join<Agenda, Colaborador> joinColaborador = root.join(Agenda_.COLABORADOR, JoinType.LEFT);

        Path<Long> idAgenda = root.get(Agenda_.ID);
        Path<LocalDate> dataAgenda = root.get(Agenda_.DATA);
        Path<Periodo> periodo = root.get(Agenda_.PERIODO);
        Path<String> diaSemana = root.get(Agenda_.DIA_SEMANA);
        Path<Boolean> contas = root.get(Agenda_.CONTAS);
        Path<Long> quantP = root.get(Agenda_.QUANT_P);
        Path<Long> quantR = root.get(Agenda_.QUANT_R);
        Path<Date> dataEntrega = root.get(Agenda_.DATA_ENTREGA);
        Path<String> observacao = root.get(Agenda_.OBSERVACAO);
        
        Path<Long> idTipoAgenda = joinTipoAgenda.get(TipoAgenda_.ID);
        Path<String> tipoAgenda = joinTipoAgenda.get(TipoAgenda_.DESCRICAO);
        
        Path<Long> idUsuarioInclusao = joinUsuarioInclusao.get(Usuario_.ID);
        Path<String> nomeUsuarioInclusao = joinUsuarioInclusao.get(Usuario_.NOME);
        Path<String> emailUsuarioInclusao = joinUsuarioInclusao.get(Usuario_.EMAIL);
        Path<LocalDate> dataInclusao = root.get(Agenda_.DATA_INCLUSAO);
        
        Path<Long> idUsuarioEdicao = joinUsuarioEdicao.get(Usuario_.ID);
        Path<String> nomeUsuarioEdicao = joinUsuarioEdicao.get(Usuario_.NOME);
        Path<String> emailUsuarioEdicao = joinUsuarioEdicao.get(Usuario_.EMAIL);
        Path<LocalDate> dataEdicao = root.get(Agenda_.DATA_EDICAO);
        
        Expression<Long> idCliente = builder().coalesce(joinClienteMedico.get(Cliente_.ID), joinClienteEnfermeiro.get(Cliente_.ID));
        Expression<String> razaoCliente = builder().coalesce(joinClienteMedico.get(Cliente_.RAZAO_SOCIAL), joinClienteEnfermeiro.get(Cliente_.RAZAO_SOCIAL));
        
        Expression<Long> idHospital = builder().coalesce(joinHospitalMedico.get(Hospital_.ID), joinHospitalEnfermeiro.get(Hospital_.ID));
        Expression<Long> razaoHospital = builder().coalesce(joinHospitalMedico.get(Hospital_.RAZAO_SOCIAL), joinHospitalEnfermeiro.get(Hospital_.RAZAO_SOCIAL));
        
        Expression<Long> idServico = builder().coalesce(joinServicoMedico.get(Servico_.ID), joinServicoEnfermeiro.get(Servico_.ID));
        Expression<String> codServico = builder().coalesce(joinServicoMedico.get(Servico_.CODIGO), joinServicoEnfermeiro.get(Servico_.CODIGO));
        Expression<Long> descricaoServico = builder().coalesce(joinServicoMedico.get(Servico_.DESCRICAO), joinServicoEnfermeiro.get(Servico_.DESCRICAO));
        
        Path<Long> idVinculoMedico = joinVinculoMedico.get(Vinculo_.ID);
        Path<Long> idMedico = joinMedico.get(Auditor_.ID);
        Path<String> nomeMedico = joinMedico.get(Auditor_.NOME);
        
        Path<Long> idVinculoEnfermeiro = joinVinculoEnfermeiro.get(Vinculo_.ID);
        Path<Long> idEnfermeiro = joinEnfermeiro.get(Auditor_.ID);
        Path<String> nomeEnfermeiro = joinEnfermeiro.get(Auditor_.NOME);

        Path<Long> idColaborador = joinColaborador.get(Colaborador_.ID);
        Path<String> nomeColaborador = joinColaborador.get(Colaborador_.NOME);
        
        criteria.multiselect(idAgenda, dataAgenda, periodo, diaSemana, contas, quantP, quantR, dataEntrega, observacao, 
        		idTipoAgenda, tipoAgenda,
        		idUsuarioInclusao, nomeUsuarioInclusao, emailUsuarioInclusao, dataInclusao, 
        		idUsuarioEdicao, nomeUsuarioEdicao, emailUsuarioEdicao, dataEdicao,
        		idCliente, razaoCliente,
        		idHospital, razaoHospital,        		
                idServico, codServico, descricaoServico, 
                idVinculoMedico, idMedico, nomeMedico,
                idVinculoEnfermeiro, idEnfermeiro, nomeEnfermeiro, 
                idColaborador, nomeColaborador);

        Predicate conjunction = builder().conjunction();
        Predicate between = builder().between(root.get(Agenda_.DATA), params.getPeriodoInicial(), params.getPeriodoFinal());
        conjunction = builder().and(conjunction, between);
        
        if(params.getTipo() != null)
        {
	        switch (params.getTipo())
			{
	        	case A:
	                Predicate equalColaborador = builder().equal(joinColaborador.get(Colaborador_.ID), UtilSecurity.decryptId(params.getId()));
	                conjunction = builder().and(conjunction, equalColaborador);
	                break;
	                
	        	case C:
	        		Predicate equalCliente = builder().equal(idCliente, UtilSecurity.decryptId(params.getId()));
	                conjunction = builder().and(conjunction, equalCliente);
	        		break;
	        		
	        	case E:
	        		Predicate equalEnfermeiro = builder().equal(joinEnfermeiro.get(Auditor_.ID), UtilSecurity.decryptId(params.getId()));
	            	conjunction = builder().and(conjunction, equalEnfermeiro);
	        		break;
	        		
	        	case H:
	        		 Predicate equalHospital = builder().equal(idHospital, UtilSecurity.decryptId(params.getId()));
	                 conjunction = builder().and(conjunction, equalHospital);
	        		break;
	        		
	        	case M:
	        		Predicate equalMedico = builder().equal(joinMedico.get(Auditor_.ID), UtilSecurity.decryptId(params.getId()));
	                conjunction = builder().and(conjunction, equalMedico);
	        		break;
			}
        }

        criteria.orderBy(builder().asc(dataAgenda), builder().asc(builder().coalesce(nomeMedico, nomeEnfermeiro)));
        criteria.where(conjunction);
        TypedQuery<Agenda> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Agenda> getClazz(){return Agenda.class;}

	@Override
	public Boolean existeAgendaParaAuditor(Long auditor, LocalDate data)
	{
		CriteriaQuery<Boolean> criteria = builder().createQuery(Boolean.class);
	    Root<Agenda> root = criteria.from(getClazz());
	    
	    Expression<Long> count = builder().count(root.get(Agenda_.ID));
	    
	    criteria.multiselect(builder().selectCase()
	    	.when(builder().gt(count, 0), true)
	    	.otherwise(false));
	    	
	    Expression<Long> coalesceAuditor = builder().coalesce(root.get(Agenda_.MEDICO).get(Vinculo_.ID), root.get(Agenda_.ENFERMEIRO).get(Vinculo_.ID));
	    Predicate equalAuditor = builder().equal(coalesceAuditor, auditor);
	    Predicate equalData = builder().equal(root.get(Agenda_.DATA), data);
	    Predicate where = builder().and(equalAuditor, equalData);
	    criteria.where(where);
	    
	    TypedQuery<Boolean> typedQuery = entityManager.createQuery(criteria);
		return typedQuery.getSingleResult();
	}
}
