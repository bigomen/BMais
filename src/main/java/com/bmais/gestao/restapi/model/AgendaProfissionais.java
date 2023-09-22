//package com.bmais.gestao.restapi.model;
//
//import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import com.bmais.gestao.restapi.restmodel.RestAgendaProfissionais;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "AGENDA_PROFISSIONAIS")
//@Data
//@NoArgsConstructor
//@DynamicInsert
//@DynamicUpdate
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
//public class AgendaProfissionais extends Model<RestAgendaProfissionais> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENDA_PROFISSIONAIS")
//    @SequenceGenerator(name = "SEQ_AGENDA_PROFISSIONAIS", sequenceName = "SEQ_AGENDA_PROFISSIONAIS", allocationSize = 1)
//    @EqualsAndHashCode.Include
//    @Column(name = "APR_ID")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "AGE_ID")
//    private Agenda agenda;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SER_ID")
//    private Servico servico;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PEJ_ID")
//    private Cliente cliente;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "HOS_ID")
//    private Hospital hospital;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "AUD_ID_MEDICO", referencedColumnName = "AUD_ID")
//    private Auditor medico;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "AUD_ID_ENFERMEIRO", referencedColumnName = "AUD_ID")
//    private Auditor enfermeiro;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "COL_ID")
//    private Colaborador colaborador;
//
//    public AgendaProfissionais(Long id, Long idServico, String descricaoServico, Long idCliente, String razaoCliente, Long idHospital, String razaoHospital,
//                               Long idMedico, String nomeMedico, Long idEnfermeiro, String nomeEnfermeiro, Long idColaborador, String nomeColaborador){
//        this.id = id;
//        this.servico = new Servico(idServico, descricaoServico);
//        this.cliente = new Cliente(idCliente, razaoCliente);
//        this.hospital = new Hospital(idHospital, razaoHospital);
//        
//        if(idMedico != null)
//        {
//        	this.medico = new Auditor(idMedico, nomeMedico);
//        }
//        
//        if(idEnfermeiro != null)
//        {
//        	this.enfermeiro = new Auditor(idEnfermeiro, nomeEnfermeiro);
//        }
//
//        if(idColaborador != null)
//        {
//        	this.colaborador = new Colaborador(idColaborador, nomeColaborador);
//        }
//    }
//
//    @Override
//    public RestAgendaProfissionais modelParaRest(){
////        return AgendaProfissionaisMapper.INSTANCE.convertToRest(this);
//    	return null;
//    }
//}
