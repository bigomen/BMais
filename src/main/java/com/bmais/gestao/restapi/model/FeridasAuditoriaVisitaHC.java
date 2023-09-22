package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.FeridasAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestFeridasAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FERIDAS_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class FeridasAuditoriaVisitaHC extends Model<RestFeridasAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FERIDAS_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_FERIDAS_AUDITORIA_VISITA_HC", sequenceName = "SEQ_FERIDAS_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "FAV_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "FAV_LOCAL")
    private String local;

    @Column(name = "FAV_TAMANHO")
    private String tamanho;

    @Column(name = "FAV_ASPECTO")
    private String aspecto;

    public FeridasAuditoriaVisitaHC(Long id, String local, String tamanho, String aspecto) {
        this.id = id;
        this.local = local;
        this.tamanho = tamanho;
        this.aspecto = aspecto;
    }

    @Override
    public RestFeridasAuditoriaVisitaHC modelParaRest(){
        return FeridasAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
