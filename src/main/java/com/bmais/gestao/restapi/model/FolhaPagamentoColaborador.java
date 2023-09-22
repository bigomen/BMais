package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.FolhaPagamentoColaboradorMapper;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaborador;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "FOLHA_PAGAMENTO_COLABORADORES")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FolhaPagamentoColaborador extends Model<RestFolhaPagamentoColaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOLHA_PAGAMENTO_COLABORADORES")
    @SequenceGenerator(name = "SEQ_FOLHA_PAGAMENTO_COLABORADORES", sequenceName = "SEQ_FOLHA_PAGAMENTO_COLABORADORES", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "FPC_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "TPC_ID")
    private TipoPagamentoColaborador tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FPG_ID")
    private FolhaPagamento folhaPagamento;

    @Column(name = "FPC_VALOR")
    private BigDecimal valor;

    @OneToOne(mappedBy = "folhaPagamentoColaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PagamentosGerados pagamentosGerados;

    public FolhaPagamentoColaborador(Long id, Long idColaborador, String nomeColaborador, Long idTipoPagamento, String tipoPagamento, BigDecimal valor) {
        this.id = id;
        this.setColaborador(new Colaborador(idColaborador, nomeColaborador));
        this.setTipoPagamento(new TipoPagamentoColaborador(idTipoPagamento, tipoPagamento));
        this.valor = valor;
    }

    @Override
    public RestFolhaPagamentoColaborador modelParaRest(){
        return FolhaPagamentoColaboradorMapper.INSTANCE.convertToRest(this);
    }
}
