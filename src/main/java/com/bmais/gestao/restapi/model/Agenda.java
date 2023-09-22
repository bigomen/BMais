package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.AgendaMapper;
import com.bmais.gestao.restapi.model.enums.Periodo;
import com.bmais.gestao.restapi.restmodel.RestAgenda;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AGENDA")
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Agenda extends Model<RestAgenda> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENDA")
    @SequenceGenerator(name = "SEQ_AGENDA", sequenceName = "SEQ_AGENDA", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "AGE_ID")
    private Long id;

    @Column(name = "AGE_DATA", updatable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private TipoAgenda tipo;

    @Column(name = "AGE_PERIODO")
    @Enumerated(EnumType.STRING)
    private Periodo periodo;

    @Column(name = "AGE_DIA_SEMANA", updatable = false)
    @Setter(value = AccessLevel.NONE)
    private String diaSemana;

    @Column(name = "AGE_CONTAS")
    private Boolean contas;

    @Column(name = "AGE_QUANT_P")
    private Long quantP;

    @Column(name = "AGE_QUANT_R")
    private Long quantR;

    @Column(name = "AGE_DATA_ENTREGA")
    private LocalDate dataEntrega;

    @Column(name = "AGE_OBSERVACAO")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID_INCLUSAO", updatable = false)
    @Setter(value = AccessLevel.NONE)
    private Usuario usuarioInclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID_EDICAO", insertable = false)
    @Setter(value = AccessLevel.NONE)
    private Usuario usuarioEdicao;

    @Column(name = "AGE_DATA_INCLUSAO", updatable = false)
    @Setter(value = AccessLevel.NONE)
    private LocalDate dataInclusao;

    @Column(name = "AGE_DATA_EDICAO", insertable = false)
    @Setter(value = AccessLevel.NONE)
    private LocalDate dataEdicao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVI_ID_MEDICO", referencedColumnName = "AVI_ID")
    private Vinculo medico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVI_ID_ENFERMEIRO", referencedColumnName = "AVI_ID")
    private Vinculo enfermeiro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;
    
	public Agenda(Long idAgenda, LocalDate dataAgenda, Periodo periodo, String diaSemana, Boolean contas, Long quantP, Long quantR, LocalDate dataEntrega, String observacao,
			Long idTipoAgenda, String tipoAgenda,
			Long idUsuarioInclusao, String nomeUsuarioInclusao, String emailUsuarioInclusao, LocalDate dataInclusao,
			Long idUsuarioEdicao, String nomeUsuarioEdicao, String emailUsuarioEdicao, LocalDate dataEdicao,
			Long idCliente, String cliente,
			Long idHospital, String hospital,
			Long idServico, String codServico, String servico,
			Long idVincluloMedico, Long idMedico, String nomeMedico,
			Long idVinculoEnfermeiro, Long idEnfermeiro, String nomeEnfermeiro,
			Long idColaborador, String nomeColaborador)
	{
	  	this.id = idAgenda;
        this.data = dataAgenda;
        this.tipo = new TipoAgenda(idTipoAgenda, tipoAgenda);
        this.periodo = periodo;
        this.diaSemana = diaSemana;
        this.contas = contas;
        this.quantP = quantP;
        this.quantR = quantR;
        this.dataEntrega = dataEntrega;
        this.observacao = observacao;
        this.dataInclusao = dataInclusao;
        this.dataEdicao = dataEdicao;
        
        if(idUsuarioEdicao != null)
        {
            this.usuarioEdicao = new Usuario(idUsuarioEdicao, nomeUsuarioEdicao, emailUsuarioEdicao);
        }
        
        this.usuarioInclusao = new Usuario(idUsuarioInclusao, nomeUsuarioInclusao, emailUsuarioInclusao);
        
        if(idVincluloMedico != null)
        {
        	this.medico = new Vinculo(idVincluloMedico);
        	this.medico.setAuditor(new Auditor(idMedico, nomeMedico));
        	this.medico.setCliente(new Cliente(idCliente, cliente));
        	this.medico.setHospital(new Hospital(idHospital, hospital));
        	this.medico.setServico(new Servico(idServico, servico, codServico));
        }
        
        if(idVinculoEnfermeiro != null)
        {
        	this.enfermeiro = new Vinculo(idVinculoEnfermeiro);
        	this.enfermeiro.setAuditor(new Auditor(idEnfermeiro, nomeEnfermeiro));
        	this.enfermeiro.setCliente(new Cliente(idCliente, cliente));
        	this.enfermeiro.setHospital(new Hospital(idHospital, hospital));
        	this.enfermeiro.setServico(new Servico(idServico, servico, codServico));
        }
        
        if(idColaborador != null)
        {
        	this.colaborador = new Colaborador(idColaborador, nomeColaborador);
        }
	}
    
    
    @PrePersist
    private void prePersist()
    {
    	this.dataInclusao = UtilData.obterDataAtual();
    	this.usuarioInclusao = new Usuario(RuleUtil.getUsuarioId());
    	this.diaSemana = UtilData.diaDaSemana(this.data);
    }
    
    @PreUpdate
    private void preUpdate()
    {
    	this.dataEdicao = UtilData.obterDataAtual();
    	this.usuarioEdicao = new Usuario(RuleUtil.getUsuarioId());
    }

    @Override
    public RestAgenda modelParaRest()
    {
        return AgendaMapper.INSTANCE.convertToRest(this);
    }
}
