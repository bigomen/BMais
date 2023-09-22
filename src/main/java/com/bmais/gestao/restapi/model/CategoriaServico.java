package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.CategoriaServicoMapper;
import com.bmais.gestao.restapi.restmodel.RestCategoriaServico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CATEGORIA_SERVICO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CategoriaServico extends Model<RestCategoriaServico> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORIA_SERVICO")
    @SequenceGenerator(name = "SEQ_CATEGORIA_SERVICO", sequenceName = "SEQ_CATEGORIA_SERVICO", allocationSize = 1)
    @Column(name = "CSE_ID")
    private Long id;

    @Column(name = "CSE_VALOR")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SER_ID")
    private Servico servico;

    @Column(name = "CSE_CATEGORIA_CODIGO")
    private Integer categoria;

    public CategoriaServico(Long id)
    {
        this.id = id;
    }

    public CategoriaServico(Long id, Integer categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public RestCategoriaServico modelParaRest(){return CategoriaServicoMapper.INSTANCE.convertToRest(this);}
}
