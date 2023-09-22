package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.CurativoMapper;
import com.bmais.gestao.restapi.restmodel.RestCurativo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURATIVO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Curativo extends Model<RestCurativo> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURATIVO")
    @SequenceGenerator(name = "SEQ_CURATIVO", sequenceName = "SEQ_CURATIVO", allocationSize = 1)
    @Column(name = "CUR_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIS_ID")
    private Visita visita;

    @Column(name = "CUR_LOCAL")
    private String local;

    @Column(name = "CUR_COMPLEXIDADE")
    private String complexidade;

    @Column(name = "CUR_TAMANHO")
    private String tamanho;

    public Curativo(Long id, String local, String complexidade, String tamanho) {
        this.id = id;
        this.local = local;
        this.complexidade = complexidade;
        this.tamanho = tamanho;
    }

    @Override
    public RestCurativo modelParaRest(){
        return CurativoMapper.INSTANCE.convertToRest(this);
    }
}
