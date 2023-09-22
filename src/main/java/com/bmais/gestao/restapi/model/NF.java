package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.NFMapper;
import com.bmais.gestao.restapi.mapper.PagamentoMapper;
import com.bmais.gestao.restapi.model.enums.TipoLancamento;
import com.bmais.gestao.restapi.restmodel.RestNF;
import com.bmais.gestao.restapi.restmodel.RestPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "NOTA_FISCAL")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class NF extends Model<RestNF> implements Serializable, Pagamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTA_FISCAL")
    @SequenceGenerator(name = "SEQ_NOTA_FISCAL", sequenceName = "SEQ_NOTA_FISCAL", allocationSize = 1)
    @Column(name = "NOT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @Column(name = "NOT_DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "NOT_DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "NOT_SERVICO")
    private String servico;

    @Column(name = "NOT_NUMERO_NOTA")
    private String numeroNota;

    @ManyToOne
    @JoinColumn(name = "SNF_ID")
    private StatusNF status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAB_ID")
    private DadosBancarios dadosBancarios;

    @Column(name = "NOT_NATUREZA")
    private String natureza;

    @Column(name = "NOT_FATURA")
    private String fatura;

    @Column(name = "NOT_MENSAGEM")
    private String mensagem;

    @Column(name = "NOT_ACRESCIMO")
    private BigDecimal acrescimo = BigDecimal.ZERO;

    @Column(name = "NOT_DESCONTO")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "NOT_DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @OneToMany(mappedBy = "nf", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<NFItem> nfItems;

    public NF(Long idNF, String numeroNota, Long idEmpresa, String razaoEmpresa, Long idCliente, String razaoCliente,
              LocalDate dataEmissao, LocalDate dataVencimento, String servico, StatusNF status){
        this.setId(idNF);
        this.setNumeroNota(numeroNota);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);
        empresa.setRazaoSocial(razaoEmpresa);
        this.setEmpresa(empresa);
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setRazaoSocial(razaoCliente);
        this.setCliente(cliente);
        this.setDataEmissao(dataEmissao);
        this.setDataVencimento(dataVencimento);
        this.setServico(servico);
        this.setStatus(status);
    }

    public NF(Long idNF, Long idEmpresa, String razaoEmpresa, Long idCliente, String razaoCliente, Long idDadosBancarios,
              Banco banco, String agencia, String conta, LocalDate dataEmissao, LocalDate dataVencimento, String servico, String numeroNota,
              StatusNF status, String natureza, String fatura, String mensagem, BigDecimal acrescimo, BigDecimal desconto,
              LocalDate dataPagamento){
        this.setId(idNF);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);
        empresa.setRazaoSocial(razaoEmpresa);
        this.setEmpresa(empresa);
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setRazaoSocial(razaoCliente);
        this.setCliente(cliente);
        DadosBancarios dadosBancarios = new DadosBancarios();
        dadosBancarios.setId(idDadosBancarios);
        dadosBancarios.setBanco(banco);
        dadosBancarios.setAgencia(agencia);
        dadosBancarios.setConta(conta);
        this.setDadosBancarios(dadosBancarios);
        this.setDataEmissao(dataEmissao);
        this.setDataVencimento(dataVencimento);
        this.setServico(servico);
        this.setNumeroNota(numeroNota);
        this.setStatus(status);
        this.setNatureza(natureza);
        this.setFatura(fatura);
        this.setMensagem(mensagem);
        this.setAcrescimo(acrescimo);
        this.setDesconto(desconto);
        this.setDataPagamento(dataPagamento);
    }

    @Override
    public RestNF modelParaRest(){
        return NFMapper.INSTANCE.convertToRest(this);
    }

    @Override
    public Long id()
    {
        return this.id;
    }
    @Override
    public LocalDate data()
    {
        return this.dataEmissao;
    }

    @Override
    public String historico()
    {
        return null;
    }

    @Override
    public String documento()
    {
        return this.numeroNota;
    }

    @Override
    public BigDecimal valor()
    {
        if(this.nfItems == null || this.nfItems.isEmpty())
        {
            return BigDecimal.ZERO;
        }

        return nfItems.stream()
                .map(it -> it.getValor().multiply(BigDecimal.valueOf(it.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(this.acrescimo)
                .subtract(this.desconto);
    }

    @Override
    public TipoLancamento lancamento()
    {
        return TipoLancamento.CR;
    }

    @Override
    public BigDecimal saldo()
    {
        return null;
    }

    @Override
    public LocalDate noBanco()
    {
        return null;
    }

    @Override
    public String movimento()
    {
        return null;
    }

    @Override
    public RestPagamento convertToPagamento()
    {
        return PagamentoMapper.INSTANCE.convertToRest(this);
    }
}
