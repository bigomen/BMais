package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.NFItemMapper;
import com.bmais.gestao.restapi.restmodel.RestNFItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "NOTA_FISCAL_ITEM")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class NFItem extends Model<RestNFItem> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTA_FISCAL_ITEM")
    @SequenceGenerator(name = "SEQ_NOTA_FISCAL_ITEM", sequenceName = "SEQ_NOTA_FISCAL_ITEM", allocationSize = 1)
    @Column(name = "NFI_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOT_ID")
    private NF nf;

    @Column(name = "NFI_SERVICO")
    private String servico;

    @Column(name = "NFI_OBSERVACAO")
    private String observacao;

    @Column(name = "NFI_QUANTIDADE")
    private Integer quantidade;

    @Column(name = "NFI_VALOR")
    private BigDecimal valor;

    @Override
    public RestNFItem modelParaRest(){
        return NFItemMapper.INSTANCE.modelParaRest(this);
    }
}
