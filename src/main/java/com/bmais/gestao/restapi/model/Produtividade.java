package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ProdutividadeMapper;
import com.bmais.gestao.restapi.restmodel.RestProdutividade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUTIVIDADE")
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Produtividade extends Model<RestProdutividade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTIVIDADE")
    @SequenceGenerator(name = "SEQ_PRODUTIVIDADE", sequenceName = "SEQ_PRODUTIVIDADE", allocationSize = 1)
    @Column(name = "PRO_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUD_ID")
    private Auditor auditor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOS_ID")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIS_ID")
    private Visita visitaConcorrente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private VisitaHomeCare visitaHomeCare;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CAP_ID_INTERNACAO", referencedColumnName = "CAP_ID")
//    private CapeanteInternacao capeanteInternacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPA_ID")
    private CapeantePSAmbulatorio capeantePSAmbulatorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SER_ID")
    private Servico servico;

    @Column(name = "PRO_COMPETENCIA")
    private String competencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPR_ID")
    private StatusProdutividade status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID_LANCAMENTO", referencedColumnName = "USU_ID")
    private Usuario usuarioLancamento;

    @Column(name = "PRO_DATA_LANCAMENTO")
    private LocalDate dataLancamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID_EDICAO", referencedColumnName = "USU_ID")
    private Usuario usuarioEdicao;

    @Column(name = "PRO_DATA_EDICAO")
    private LocalDate dataEdicao;

    @Override
    public RestProdutividade modelParaRest(){
        return ProdutividadeMapper.INSTANCE.convertToRest(this);
    }
}
