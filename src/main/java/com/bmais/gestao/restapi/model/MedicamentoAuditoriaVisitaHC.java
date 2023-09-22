package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.MedicamentoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestMedicamentoAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEDICAMENTO_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class MedicamentoAuditoriaVisitaHC extends Model<RestMedicamentoAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEDICAMENTO_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_MEDICAMENTO_AUDITORIA_VISITA_HC", sequenceName = "SEQ_MEDICAMENTO_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "MEC_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "MEC_ORIGEM")
    private String origem;

    @Column(name = "MEC_DESCRICAO")
    private String descricao;

    @Column(name = "MEC_MEDICAMENTO")
    private String medicamento;

    @Column(name = "MEC_VIA")
    private String via;

    @Column(name = "MEC_FREQUENCIA")
    private String frequencia;

    @Column(name = "MEC_PERIODO")
    private String periodo;

    public MedicamentoAuditoriaVisitaHC(Long id, String origem, String descricao, String medicamento, String via, String frequencia, String periodo) {
        this.id = id;
        this.origem = origem;
        this.descricao = descricao;
        this.medicamento = medicamento;
        this.via = via;
        this.frequencia = frequencia;
        this.periodo = periodo;
    }

    @Override
    public RestMedicamentoAuditoriaVisitaHC modelParaRest(){
        return MedicamentoAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
