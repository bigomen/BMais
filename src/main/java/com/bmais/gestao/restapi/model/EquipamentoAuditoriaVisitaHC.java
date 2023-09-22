package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.EquipamentoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestEquipamentoAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EQUIPAMENTO_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class EquipamentoAuditoriaVisitaHC extends Model<RestEquipamentoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPAMENTO_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_EQUIPAMENTO_AUDITORIA_VISITA_HC", sequenceName = "SEQ_EQUIPAMENTO_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "EAV_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "EAV_EQUIPAMENTO")
    private String equipamento;

    @Column(name = "EAV_PROPRIO")
    private Boolean proprio;

    @Column(name = "EAV_OBSERVACAO")
    private String observacao;

    public EquipamentoAuditoriaVisitaHC(Long id, String equipamento, Boolean proprio, String observacao) {
        this.id = id;
        this.equipamento = equipamento;
        this.proprio = proprio;
        this.observacao = observacao;
    }

    @Override
    public RestEquipamentoAuditoriaVisitaHC modelParaRest(){
        return EquipamentoAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
