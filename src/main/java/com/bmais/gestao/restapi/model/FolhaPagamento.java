package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.FolhaPagamentoMapper;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FOLHA_PAGAMENTO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FolhaPagamento extends Model<RestFolhaPagamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOLHA_PAGAMENTO")
    @SequenceGenerator(name = "SEQ_FOLHA_PAGAMENTO", sequenceName = "SEQ_FOLHA_PAGAMENTO", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "FPG_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @Column(name = "FPG_DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "FPG_DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAB_ID")
    private DadosBancarios dadosBancarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USU_ID")
    private Usuario usuario;

    public FolhaPagamento(Long id, LocalDate dataVencimento, LocalDate dataEmissao, Long idEmpresa, String razaoEmpresa,
                          Long idDadoBancario, Banco banco, String agencia, String conta, Long idUsuario, String emailUsuario){
        this.setId(id);
        this.setDataVencimento(dataVencimento);
        this.setDataEmissao(dataEmissao);
        this.setEmpresa(new Empresa(idEmpresa, razaoEmpresa));
        this.setDadosBancarios(new DadosBancarios(idDadoBancario, banco, agencia, conta));
        this.setUsuario(new Usuario(idUsuario, emailUsuario));
    }

    @Override
    public RestFolhaPagamento modelParaRest(){
        return FolhaPagamentoMapper.INSTANCE.convertToRest(this);
    }
}
