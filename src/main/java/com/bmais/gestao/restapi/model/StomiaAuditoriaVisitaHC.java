package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StomiaAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestStomiaAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STOMIA_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class StomiaAuditoriaVisitaHC extends Model<RestStomiaAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOMIA_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_STOMIA_AUDITORIA_VISITA_HC", sequenceName = "SEQ_STOMIA_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "SAV_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "SAV_STOMIA")
    private String stomia;

    @Column(name = "SAV_OBSERVACAO")
    private String observacao;

    public StomiaAuditoriaVisitaHC(Long id, String stomia, String observacao) {
        this.id = id;
        this.stomia = stomia;
        this.observacao = observacao;
    }

    @Override
    public RestStomiaAuditoriaVisitaHC modelParaRest(){
        return StomiaAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
