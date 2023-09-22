package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ServicoPrestadoAuditoriaVisitaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestServicoPrestadoAuditoriaVisitaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICO_PRESTADO_AUDITORIA_VISITA_HC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class ServicoPrestadoAuditoriaVisitaHC extends Model<RestServicoPrestadoAuditoriaVisitaHC> implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICO_PRESTADO_AUDITORIA_VISITA_HC")
    @SequenceGenerator(name = "SEQ_SERVICO_PRESTADO_AUDITORIA_VISITA_HC", sequenceName = "SEQ_SERVICO_PRESTADO_AUDITORIA_VISITA_HC", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "SPA_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VHC_ID")
    private AuditoriaVisitaHomeCare visita;

    @Column(name = "SPA_PROFISSIONAL")
    private String profissional;

    @Column(name = "SPA_QUANTIDADE")
    private Integer quantidade;

    @Column(name = "SPA_FREQUENCIA")
    private String frequencia;

    @Column(name = "SPA_SERVICO")
    private String servico;

    @Column(name = "SPA_OBSERVACAO")
    private String observacao;

    @Column(name = "SPA_ASSISTENCIA_ADEQUADA")
    private Boolean assistenciaAdequada;

    public ServicoPrestadoAuditoriaVisitaHC(Long id, String profissional, Integer quantidade, String frequencia, String servico, String observacao, Boolean assistenciaAdequada) {
        this.id = id;
        this.profissional = profissional;
        this.quantidade = quantidade;
        this.frequencia = frequencia;
        this.servico = servico;
        this.observacao = observacao;
        this.assistenciaAdequada = assistenciaAdequada;
    }

    @Override
    public RestServicoPrestadoAuditoriaVisitaHC modelParaRest(){
        return ServicoPrestadoAuditoriaVisitaHCMapper.INSTANCE.convertToRest(this);
    }

}
