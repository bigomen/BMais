package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AuditoriaVisitaHomeCareMapper;
import com.bmais.gestao.restapi.restmodel.RestAuditoriaVisitaHomeCare;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "AUDITORIA_VISITA_HOMECARE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class AuditoriaVisitaHomeCare extends Model<RestAuditoriaVisitaHomeCare> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VHC_ID")
    @Include
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    @ToString.Exclude
    private VisitaHomeCare visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUD_ID")
    @ToString.Exclude
    private Auditor auditor;

    @Column(name = "AVH_DT_AUDITORIA")
    private LocalDate dataAuditoria;

    @Column(name = "AVH_DT_ULTIMA_INTERNACAO")
    private LocalDate dataUltimaInternacao;

    @Column(name = "AVH_COMPLEMENTO")
    private String complemento;

    @Column(name = "AVH_INFORMANTE")
    private String informante;

    @Column(name = "AVH_HISTORICO_CLINICO_GERAL")
    private String historicoClinicoGeral;

    @Column(name = "AVH_EVOLUCAO_ESTADO_GERAL")
    private String evolucaoEstadoGeral;

    @Column(name = "AVH_DADOS_VITAIS_PA")
    private String dadosVitaisPa;

    @Column(name = "AVH_TEMPERATURA")
    private String temperatura;

    @Column(name = "AVH_ESTADO_CLINICO_ATUAL")
    private String estadoClinicoGeral;

    @Column(name = "AVH_NIVEL_CONCIENCIA")
    private String nivelConciencia;

    @Column(name = "AVH_MOBILIDADE")
    private String mobilidade;

    @Column(name = "AVH_SNG")
    private Boolean sng;

    @Column(name = "AVH_SNE")
    private Boolean sne;

    @Column(name = "AVH_SVA")
    private Boolean sva;

    @Column(name = "AVH_XDIA")
    private Integer xdia;

    @Column(name = "AVH_svd")
    private Boolean svd;

    @Column(name = "AVH_URIPEN")
    private Boolean uripen;

    @Column(name = "AVH_CONCENTRADOR")
    private Boolean concentrador;

    @Column(name = "AVH_CONCENTRADOR_CONTINUO")
    private Boolean concentradorContinuo;

    @Column(name = "AVH_CONCENTRADOR_HDIA")
    private Integer concentradorHdia;

    @Column(name = "AVH_BIPAP")
    private Boolean bipap;

    @Column(name = "AVH_BIPAP_HDIA")
    private Integer bipapHdia;

    @Column(name = "AVH_CPAP")
    private Boolean cpap;

    @Column(name = "AVH_CPAP_HDIA")
    private Integer cpapHdia;

    @Column(name = "AVH_MONITOR")
    private Boolean monitor;

    @Column(name = "AVH_RESPIRADOR")
    private Boolean respirador;

    @Column(name = "AVH_RESPIRADOR_DESCRICAO")
    private String respiradorDescricao;

    @Column(name = "AVH_CONTINUO")
    private Boolean continuo;

    @Column(name = "AVH_INTERMITENTE")
    private Boolean intermitente;

    @Column(name = "AVH_INTERMITENTE_H_DIA")
    private Integer intermitenteHdia;

    @Column(name = "AVH_TROCA_TORPEDO")
    private Boolean trocaTorpedo;

    @Column(name = "AVH_TROCA_TORPEDO_QTD")
    private Integer trocaTorpedoQuant;

    @Column(name = "AVH_TROCA_TORPEDO_PERIODO")
    private String trocaTorpedoPeriodo;

    @Column(name = "AVH_EXISTE_CUIDADOR")
    private Boolean existeCuidador;

    @Column(name = "AVH_NOME_CUIDADOR")
    private String nomeCuidador;

    @Column(name = "AVH_HABILITADO_CUIDAR")
    private Boolean habilitadoCuidar;

    @Column(name = "AVH_CUIDADOR_RECEBE_INSTRUCAO")
    private Boolean cuidadorRecebeInstrucao;

    @Column(name = "AVH_CUIDADOR_CONDICOES_APRENDER")
    private Boolean cuidadorCondicoesAprender;

    @Column(name = "AVH_CUIDADOR_DELEGA")
    private Boolean cuidadorDelega;

    @Column(name = "AVH_SERV_SATISFATORIO")
    private Boolean servSatisfatorio;

    @Column(name = "AVH_PQ_SERV_NAO_SATISFATORIO")
    private String servNaoSatisfatorio;

    @Column(name = "AVH_OBSERVACAO")
    private String observacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SINTOMA_QUADRO_CLINICO_AUDIT_VISITA_HC",
            joinColumns = @JoinColumn(name = "VHC_ID"),
            inverseJoinColumns = @JoinColumn(name = "SQC_ID"))
    @ToString.Exclude
    private Collection<SintomaQuadroClinico> sintomas;

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<DiagnosticoAuditoriaVisitaHC> diagnosticos = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<FeridasAuditoriaVisitaHC> feridas = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<StomiaAuditoriaVisitaHC> stomias = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<ServicoPrestadoAuditoriaVisitaHC> servicosPrestados = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<MaterialAuditoriaVisitaHC> materiais = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<MedicamentoAuditoriaVisitaHC> medicamentos = new ArrayList<>();

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<EquipamentoAuditoriaVisitaHC> equipamentos = new ArrayList<>();

    @OneToOne(mappedBy = "visita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @Setter(value = AccessLevel.NONE)
    @ToString.Exclude
    private AvaliacaoDependenciaVisitaHC avaliacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CONCLUSAO_AUDITORIA_AUDIT_VISITA_HC",
            joinColumns = @JoinColumn(name = "VHC_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAV_ID"))
    @ToString.Exclude
    private Collection<ConclusaoAuditoriaHC> conclusoes;

    @Override
    public RestAuditoriaVisitaHomeCare modelParaRest(){
        return AuditoriaVisitaHomeCareMapper.INSTANCE.convertToRest(this);
    }
    
	public void setAvaliacao(AvaliacaoDependenciaVisitaHC avaliacao)
	{
		avaliacao.setVisita(this);
		this.avaliacao = avaliacao;
	}
    
    public void addDiagnosticos(DiagnosticoAuditoriaVisitaHC diagnostico)
    {
    	if(this.diagnosticos == null)
    	{
    		this.diagnosticos = new ArrayList<>();
    	}
    	
    	diagnostico.setVisita(this);
    	this.diagnosticos.add(diagnostico);
    }
    
    public void addFeridas(FeridasAuditoriaVisitaHC ferida)
    {
    	if(this.feridas == null)
    	{
    		this.feridas = new ArrayList<>();
    	}
    	
    	ferida.setVisita(this);
    	this.feridas.add(ferida);
    }
    
    public void addStomias(StomiaAuditoriaVisitaHC stomia)
    {
    	if(this.stomias == null)
    	{
    		this.stomias = new ArrayList<>();
    	}
    	
    	stomia.setVisita(this);
    	this.stomias.add(stomia);
    }
    
    public void addServicosPrestados(ServicoPrestadoAuditoriaVisitaHC servico)
    {
    	if(this.servicosPrestados == null)
    	{
    		this.servicosPrestados = new ArrayList<>();
    	}
    	
    	servico.setVisita(this);
    	this.servicosPrestados.add(servico);
    }
    
    public void addMateriais(MaterialAuditoriaVisitaHC material)
    {
    	if(this.materiais == null)
    	{
    		this.materiais = new ArrayList<>();
    	}
    	
    	material.setVisita(this);
    	this.materiais.add(material);
    }
    
    public void addMedicamentos(MedicamentoAuditoriaVisitaHC medicamento)
    {
    	if(this.medicamentos == null)
    	{
    		this.medicamentos = new ArrayList<>();
    	}
    	
    	medicamento.setVisita(this);
    	this.medicamentos.add(medicamento);
    }
    
    public void addEquipamentos(EquipamentoAuditoriaVisitaHC equipamento)
    {
    	if(this.equipamentos == null)
    	{
    		this.equipamentos = new ArrayList<>();
    	}
    	
    	equipamento.setVisita(this);
    	this.equipamentos.add(equipamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuditoriaVisitaHomeCare that = (AuditoriaVisitaHomeCare) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
