package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AcomodacaoVisitaMapper;
import com.bmais.gestao.restapi.restmodel.RestAcomodacaoVisita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ACOMODACAO_VISITA")
@Getter
@Setter
@NoArgsConstructor
public class AcomodacaoVisita extends Model<RestAcomodacaoVisita> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACOMODACAO_VISITA")
    @SequenceGenerator(name = "SEQ_ACOMODACAO_VISITA", sequenceName = "SEQ_ACOMODACAO_VISITA", allocationSize = 1)
    @Column(name = "ACV_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIS_ID")
    private Visita visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACO_ID")
    private Acomodacao acomodacao;

    @Column(name = "ACV_DH_INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "ACV_DH_FIM")
    private LocalDateTime dataFim;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AcomodacaoVisita that = (AcomodacaoVisita) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public AcomodacaoVisita(Long id, Acomodacao acomodacao, LocalDateTime dataInicio, LocalDateTime dataFim){
        this.id = id;
        this.acomodacao = acomodacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public RestAcomodacaoVisita modelParaRest(){
        return AcomodacaoVisitaMapper.INSTANCE.convertToRest(this);
    }
}
