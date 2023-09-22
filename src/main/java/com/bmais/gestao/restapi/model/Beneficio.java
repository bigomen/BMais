package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.BeneficioMapper;
import com.bmais.gestao.restapi.restmodel.RestBeneficio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BENEFICIO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class Beneficio extends Model<RestBeneficio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BENEFICIO")
    @SequenceGenerator(name = "SEQ_BENEFICIO", sequenceName = "SEQ_BENEFICIO", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "BEN_ID")
    private Long id;

    @Column(name = "BEN_DESCRICAO")
    private String descricao;

    @Column(name = "BEN_FAIXA_ETARIA_INICIAL")
    private Long faixaEtariaInicial;

    @Column(name = "BEN_FAIXA_ETARIA_FINAL")
    private Long faixaEtariaFinal;

    @Column(name = "BEN_VALOR_OFERECIDO")
    private BigDecimal valorOferecido;

    @Column(name = "BEN_VALOR_BENEFICIO")
    private BigDecimal valorBeneficio;

    public Beneficio(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public RestBeneficio modelParaRest(){
        return BeneficioMapper.INSTANCE.convertToRest(this);
    }
}
