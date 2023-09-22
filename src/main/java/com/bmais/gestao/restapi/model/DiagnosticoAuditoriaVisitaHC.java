package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.DiagnosticoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestDiagnosticoAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIAGNOSTICO_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class DiagnosticoAuditoriaVisitaHC extends Model<RestDiagnosticoAuditoriaVisitaHC> implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIAGNOSTICO_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_DIAGNOSTICO_AUDITORIA_VISITA_HC", sequenceName = "SEQ_DIAGNOSTICO_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "DAV_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CID_ID")
    private CID cid;

    @Column(name = "DAV_PRINCIPAL")
    private Boolean principal;

    public DiagnosticoAuditoriaVisitaHC(Long id, Boolean principal, Long cidId, String cidDescricao){
        this.setId(id);
        this.setPrincipal(principal);
        this.setCid(new CID(cidId, cidDescricao));
    }

    @Override
    public RestDiagnosticoAuditoriaVisitaHC modelParaRest(){
        return DiagnosticoAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }
}
