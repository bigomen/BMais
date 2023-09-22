package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.PagamentosGeradosMapper;
import com.bmais.gestao.restapi.restmodel.RestPagamentosGerados;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PAGAMENTOS_GERADOS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class PagamentosGerados extends Model<RestPagamentosGerados> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAGAMENTOS_GERADOS")
    @SequenceGenerator(name = "SEQ_PAGAMENTOS_GERADOS", sequenceName = "SEQ_PAGAMENTOS_GERADOS", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "PGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FPG_ID")
    private FolhaPagamento folhaPagamento;

    @Column(name = "PGE_LANCAMENTO")
    private Long lancamento;

    @Column(name = "PGE_MOVIMENTACAO_BANCARIA")
    private Long movimentacaoBancaria;

    @Column(name = "PGE_DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "PGE_DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "PGE_VALOR")
    private BigDecimal valor;

    @Column(name = "PGE_DOCUMENTO")
    private String documento;

    @Column(name = "PGE_DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Column(name = "PGE_VALOR_PAGO")
    private BigDecimal valorPago;

    @Column(name = "PGE_HISTORICO")
    private String historico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "SPG_ID")
    private StatusPagamentosGerados status;

    @Column(name = "PGE_FAVORECIDO")
    private String favorecido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "FPC_ID")
    private FolhaPagamentoColaborador folhaPagamentoColaborador;

    public PagamentosGerados(Long id, Long lancamento, LocalDate dataVencimento, String documento, BigDecimal valorPago, String historico, Long idCliente, String razaoCliente) {
        this.id = id;
        this.lancamento = lancamento;
        this.dataVencimento = dataVencimento;
        this.documento = documento;
        this.valorPago = valorPago;
        this.historico = historico;
        this.cliente = new Cliente(idCliente, razaoCliente);
    }

    public PagamentosGerados(Long id, Long lancamento, Long movimentacaoBancaria, LocalDate dataEmissao, LocalDate dataVencimento, String documento, BigDecimal valorPago, String historico, Long idCliente, String razaoCliente, StatusPagamentosGerados status) {
        this.id = id;
        this.lancamento = lancamento;
        this.movimentacaoBancaria = movimentacaoBancaria;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.documento = documento;
        this.valorPago = valorPago;
        this.historico = historico;
        this.setCliente(new Cliente(idCliente, razaoCliente));
        this.status = status;
    }

    @Override
    public RestPagamentosGerados modelParaRest(){
        return PagamentosGeradosMapper.INSTANCE.convertToRest(this);
    }
}
