package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.MaterialAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestMaterialAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MATERIAL_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class MaterialAuditoriaVisitaHC extends Model<RestMaterialAuditoriaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MATERIAL_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_MATERIAL_AUDITORIA_VISITA_HC", sequenceName = "SEQ_MATERIAL_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "MAC_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "MAC_MATERIAL")
    private String material;

    @Column(name = "MAC_QUANTIDADE")
    private Integer quantidade;

    @Column(name = "MAC_FREQUENCIA")
    private String frequencia;

    @Column(name = "MAC_PRODUTO")
    private String produto;

    @Column(name = "MAC_OBSERVACAO")
    private String observacao;

    public MaterialAuditoriaVisitaHC(Long id, String material, Integer quantidade, String frequencia, String produto, String observacao) {
        this.id = id;
        this.material = material;
        this.quantidade = quantidade;
        this.frequencia = frequencia;
        this.produto = produto;
        this.observacao = observacao;
    }

    @Override
    public RestMaterialAuditoriaVisitaHC modelParaRest(){
        return MaterialAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
