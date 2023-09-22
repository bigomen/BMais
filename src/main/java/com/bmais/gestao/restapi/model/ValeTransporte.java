package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.ValeTransporteMapper;
import com.bmais.gestao.restapi.restmodel.RestValeTransporte;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "VALE_TRANSPORTE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ValeTransporte extends Model<RestValeTransporte> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VALE_TRANSPORTE")
    @SequenceGenerator(name = "SEQ_VALE_TRANSPORTE", sequenceName = "SEQ_VALE_TRANSPORTE", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "VTR_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @Column(name = "VTR_DATA")
    private LocalDate data;

    @Column(name = "VTR_QUANTIDADE")
    private Long quantidade;

    @ManyToOne
    @JoinColumn(name = "TVT_ID")
    private TipoValeTransporte tipo;

    @Column(name = "VTR_OBSERVACAO")
    private String observacao;

    public ValeTransporte(Long id, LocalDate data, Long quantidade, TipoValeTransporte tipo, String observacao) {
        this.id = id;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.observacao = observacao;
    }

    @Override
    public RestValeTransporte modelParaRest(){
        return ValeTransporteMapper.INSTANCE.convertToRest(this);
    }
}
