package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ComponenteAltoCustoMapper;
import com.bmais.gestao.restapi.restmodel.RestComponenteAltoCusto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMPONENTE_ALTO_CUSTO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ComponenteAltoCusto extends Model<RestComponenteAltoCusto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPONENTE_ALTO_CUSTO")
    @SequenceGenerator(name = "SEQ_COMPONENTE_ALTO_CUSTO", sequenceName = "SEQ_COMPONENTE_ALTO_CUSTO", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "CAC_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIS_ID")
    private Visita visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TCO_ID")
    private TipoComponente tipo;

    @Column(name = "CAC_MARCA_TIPO")
    private String marcaTipo;

    @Column(name = "CAC_QUANTIDADE")
    private Long quantidade;

    public ComponenteAltoCusto(Long id, TipoComponente tipo, String marcaTipo, Long quantidade) {
        this.id = id;
        this.tipo = tipo;
        this.marcaTipo = marcaTipo;
        this.quantidade = quantidade;
    }

    @Override
    public RestComponenteAltoCusto modelParaRest(){
        return ComponenteAltoCustoMapper.INSTANCE.convertToRest(this);
    }
}
