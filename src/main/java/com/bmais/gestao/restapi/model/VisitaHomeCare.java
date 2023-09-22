package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.VisitaHomeCareMapper;
import com.bmais.gestao.restapi.restmodel.RestVisitaHomeCare;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "VISITA_HOMECARE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class VisitaHomeCare extends Model<RestVisitaHomeCare> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VISITA_HOMECARE")
    @SequenceGenerator(name = "SEQ_VISITA_HOMECARE", sequenceName = "SEQ_VISITA_HOMECARE", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "VHC_ID")
    private Long id;

    @Column(name = "VHC_DT_INCLUSAO", updatable = false)
    private LocalDate dataInclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID", updatable = false)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SVI_ID")
    @ToString.Exclude
    private StatusVisita status;

    @Column(name = "VHC_NUMERO_VISITA", updatable = false)
    private Long numeroVisita;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "PRO_ID")
    @ToString.Exclude
    private ProntuarioVisitaHomeCare prontuario;

    @OneToOne(mappedBy = "visita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ToString.Exclude
    private AuditoriaVisitaHomeCare auditoria;
    
    public void setAuditoria(AuditoriaVisitaHomeCare auditoria)
    {
    	auditoria.setVisita(this);
    	this.auditoria = auditoria;
    }

    @PrePersist
    private void prePersist()
    {
        this.status = new StatusVisita(StatusVisita.RASCUNHO);
        this.usuario = new Usuario(RuleUtil.getUsuarioId());
        this.dataInclusao = UtilData.obterDataAtual();
    }
    
    public VisitaHomeCare(Long numeroVisita){
        this.setNumeroVisita(numeroVisita);
    }

    public VisitaHomeCare(Long id, LocalDate dataInclusao, Long numeroVisita, String descricao){
        this.setDataInclusao(dataInclusao);
        this.setNumeroVisita(numeroVisita);
        this.setStatus(new StatusVisita(descricao));
        this.id = id;
    }

    public VisitaHomeCare(Long id, Long numero, Long numeroProntuario, String nomePaciente, String matriculaPaciente, String razaoCliente, String hospital, String status,
                          LocalDate dataVisita, Long idAuditor, String nomeAuditor, String observacao){
        this.setId(id);
        this.numeroVisita = numero;
        ProntuarioVisitaHomeCare prontuarioVisitaHomeCare = new ProntuarioVisitaHomeCare(numeroProntuario);
        AuditoriaVisitaHomeCare auditoriaVisitaHomeCare = new AuditoriaVisitaHomeCare();
        Auditor auditor = new Auditor();
        auditor.setId(idAuditor);
        auditor.setNome(nomeAuditor);
        auditoriaVisitaHomeCare.setDataAuditoria(dataVisita);
        auditoriaVisitaHomeCare.setObservacao(observacao);
        auditoriaVisitaHomeCare.setAuditor(auditor);
        Paciente paciente = new Paciente();
        Cliente cliente = new Cliente();
        paciente.setNome(nomePaciente);
        paciente.setMatricula(matriculaPaciente);
        cliente.setRazaoSocial(razaoCliente);
        paciente.setCliente(cliente);
        prontuarioVisitaHomeCare.setPaciente(paciente);
        prontuarioVisitaHomeCare.setHospital(hospital);
        this.setStatus(new StatusVisita(status));
        this.setProntuario(prontuarioVisitaHomeCare);
        this.setAuditoria(auditoriaVisitaHomeCare);
    }

    @Override
    public RestVisitaHomeCare modelParaRest(){
        return VisitaHomeCareMapper.INSTANCE.convertToRest(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VisitaHomeCare that = (VisitaHomeCare) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
