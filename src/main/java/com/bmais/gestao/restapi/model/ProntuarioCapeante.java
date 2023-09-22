package com.bmais.gestao.restapi.model;

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
import com.bmais.gestao.restapi.mapper.ProntuarioCapeanteMapper;
import com.bmais.gestao.restapi.model.enums.TipoAlta;
import com.bmais.gestao.restapi.restmodel.RestProntuarioCapeante;
import com.bmais.gestao.restapi.utility.RuleUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
@Table(name = "CAPEANTE_INTERNACAO")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProntuarioCapeante extends Model<RestProntuarioCapeante>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAPEANTE_INTERNACAO")
	@SequenceGenerator(name = "SEQ_CAPEANTE_INTERNACAO", sequenceName = "SEQ_CAPEANTE_INTERNACAO", allocationSize = 1)
	@Column(name = "CIN_ID")
	@EqualsAndHashCode.Include
	private Long id;
	
    @Column(name = "CIN_NUM_CONTA_HOSPITALAR")
    private String contaHospitalar;

    @Column(name = "CIN_TP_ALTA")
    @Enumerated(EnumType.STRING)
    private TipoAlta tipoAlta;

    @Column(name = "CIN_DATA_INTERNACAO")
    private LocalDate dataInternacao;

    @Column(name = "CIN_DATA_ALTA")
    private LocalDate dataAlta;

    @Column(name = "CIN_SENHA_INTERNACAO")
    private String senhaInternacao;
    
    @Column(name = "CIN_SENHA_CONVENIO")
    private String senhaConvenio;
    
    @Column(name = "CIN_OBITO")
    private boolean obito = false;
    
    @Column(name = "CIN_RELATORIO")
    private boolean relatorio = false;
    
    @Column(name = "CIN_OBSERVACAO")
    private String observacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAC_ID", updatable = false)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PJH_ID")
    private HospitalCliente hospital;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCA_ID")
    private StatusCapeante status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID", updatable = false)
    @Setter(value = AccessLevel.NONE)
    private Usuario usuario;
    
    @PrePersist
    private void prePersist()
    {
    	this.usuario = new Usuario(RuleUtil.getUsuarioId());
    }
    
    @PreUpdate
    private void preUpdate()
    {
    }
    
    public ProntuarioCapeante(Long id, Long idUsuario){
        this.setId(id);
        this.usuario = new Usuario(idUsuario);
    }

    public ProntuarioCapeante(Long id, String paciente, String cliente, String hospital, LocalDate dataInternacao, 
    		LocalDate dataAlta, TipoAlta tipoAlta, Boolean obito, String status)
    {
    	this.id = id;
    	this.paciente = new Paciente(null, paciente);
    	this.paciente.setCliente(new Cliente(null, cliente));
    	this.hospital = new HospitalCliente();
    	this.hospital.setHospital(new Hospital(null, hospital));
    	this.dataAlta = dataAlta;
    	this.dataInternacao = dataInternacao;
    	this.tipoAlta = tipoAlta;
    	this.obito = obito;
    	this.status = new StatusCapeante(status);
    }
    
    public RestProntuarioCapeante modelParaRest(){return ProntuarioCapeanteMapper.INSTANCE.convertToRest(this);}
}
