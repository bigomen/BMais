package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ProntuarioVisitaHomeCareMapper;
import com.bmais.gestao.restapi.restmodel.RestProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PRONTUARIO_VISITA_HOMECARE")
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ProntuarioVisitaHomeCare extends Model<RestProntuarioVisitaHomeCare>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRONTUARIO")
	@SequenceGenerator(name = "SEQ_PRONTUARIO", sequenceName = "SEQ_PRONTUARIO_VISITA_HOMECARE", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PRO_TP_HOMECARE")
    private String tipoHomecare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAC_ID", updatable = false)
    @ToString.Exclude
    private Paciente paciente;

    @Column(name = "PRO_EMPRESA")
    private String empresa;

    @Column(name = "PRO_INICIO")
    private LocalDate inicio;

    @Column(name = "PRO_ALTA")
    private LocalDate alta;

    @Column(name = "PRO_OBITO")
    private Boolean obito;
    
    @Column(name = "PRO_DT_INCLUSAO")
    private LocalDate dataInclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID", updatable = false)
    @ToString.Exclude
    private Usuario usuario;
    
	@ManyToOne
	@JoinColumn(name = "SPH_ID")
	private StatusProntuarioVisitaHomeCare status;
    
    @ManyToOne
    @JoinColumn(name = "PMA_ID")
    private ProntuarioMotivoAlta motivoAlta;

    @Column(name = "PRO_HOSPITAL")
    private String hospital;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "END_ID")
    @ToString.Exclude
    private Endereco endereco;

    public RestProntuarioVisitaHomeCare modelParaRest(){
        return ProntuarioVisitaHomeCareMapper.INSTANCE.convertToRest(this);
    }
    
    @PrePersist
    private void prePersist()
    {
    	this.status = new StatusProntuarioVisitaHomeCare(StatusProntuarioVisitaHomeCare.ATIVO);
    	this.usuario = new Usuario(RuleUtil.getUsuarioId());
    }
    
    public ProntuarioVisitaHomeCare(Long idProntuario, String nomePaciente, String empresa, String razaoCliente, String hospital, 
    		LocalDate dataInicio, LocalDate dataAlta, String motivoAlta, Boolean obito, String status){
    	this.setId(idProntuario);
		this.setInicio(dataInicio);
		this.setEmpresa(empresa);
		this.setAlta(dataAlta);
		this.setObito(obito);
		this.setStatus(new StatusProntuarioVisitaHomeCare(null, status));
		this.setMotivoAlta(new ProntuarioMotivoAlta(null, motivoAlta));
		this.setHospital(hospital);
		Cliente cliente = new Cliente(null, razaoCliente);
		Paciente paciente = new Paciente(null, nomePaciente);
		paciente.setCliente(cliente);
		this.setPaciente(paciente);
	}

	public ProntuarioVisitaHomeCare(Long id)
	{
		super();
		this.id = id;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProntuarioVisitaHomeCare that = (ProntuarioVisitaHomeCare) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
