package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.AvaliacaoDependenciaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestAvaliacaoDependenciaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AVALIACAO_DEPENDENCIA_VHC")
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AvaliacaoDependenciaVisitaHC extends Model<RestAvaliacaoDependenciaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VHC_ID")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "VHC_SONDA")
    private String sonda;

    @Column(name = "VHC_STOMIA")
    private String stomia;

    @Column(name = "VHC_ACESSO_VENOSO")
    private String acessoVenoso;

    @Column(name = "VHC_ADM_MEDICAMENTO")
    private String admMedicamento;

    @Column(name = "VHC_APARELHAGEM")
    private String aparelhagem;

    @Column(name = "VHC_CURATIVO")
    private String curativo;

    @Column(name = "VHC_HIGIENE_PESSOAL")
    private String higienePessoal;

    @Column(name = "VHC_DIETA")
    private String dieta;

    @Column(name = "VHC_MOBILIZACAO")
    private String mobilizacao;

    @Column(name = "VHC_VIGILANCIA_PESSOAL")
    private String vigilanciaPessoal;

    @Column(name = "VHC_SONDA_CUIDADOR")
    private Boolean sondaCuidador;

    @Column(name = "VHC_STOMIA_CUIDADOR")
    private Boolean stomiaCuidador;

    @Column(name = "VHC_ACESSO_VENOSO_CUIDADOR")
    private Boolean acessoVenosoCuidador;

    @Column(name = "VHC_ADM_MEDICAMENTO_CUIDADOR")
    private Boolean admMedicamentoCuidador;

    @Column(name = "VHC_APARELHAGEM_CUIDADOR")
    private Boolean aparelhagemCuidador;

    @Column(name = "VHC_CURATIVO_CUIDADOR")
    private Boolean curativoCuidador;

    @Column(name = "VHC_HIGIENE_PESSOAL_CUIDADOR")
    private Boolean higienePessoalCuidador;

    @Column(name = "VHC_DIETA_CUIDADOR")
    private Boolean dietaCuidador;

    @Column(name = "VHC_MOBILIZACAO_CUIDADOR")
    private Boolean mobilizacaoCuidador;

    @Column(name = "VHC_VIGILANCIA_PESSOAL_CUIDADOR")
    private Boolean vigilanciaPessoalCuidador;

    public RestAvaliacaoDependenciaVisitaHC modelParaRest(){
        return AvaliacaoDependenciaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
