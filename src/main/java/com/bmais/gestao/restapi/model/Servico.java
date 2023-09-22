package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.ServicoMapper;
import com.bmais.gestao.restapi.model.enums.ClientePrestador;
import com.bmais.gestao.restapi.restmodel.RestServico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "SERVICO")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Servico extends Model<RestServico> implements Serializable {

    private static final long serialVersionUID = 1L;

    //Cliente
    public static final Long VISITA_CONCORRENTE = -1L;
    public static final Long VISITA_HOMECARE = -2L;
    public static final Long CAPEANTE_INTERNACAO = -3L;
    public static final Long CAPEANTE_PS = -4L;

    //Prestador
    public static final Long VISITA_CONCORRENTE_P = -5L;
    public static final Long VISITA_HOMECARE_P = -6L;
    public static final Long CAPEANTE_INTERNACAO_P = -7L;
    public static final Long CAPEANTE_PS_P = -8L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICO")
    @SequenceGenerator(name = "SEQ_SERVICO", sequenceName = "SEQ_SERVICO", allocationSize = 1)
    @Column(name = "SER_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "SER_CODIGO")
    private String codigo;

    @Column(name = "SER_DESCRICAO")
    private String descricao;

    @Column(name = "SER_CLIENTE_PRESTADOR")
    @Enumerated(EnumType.STRING)
    private ClientePrestador clientePrestador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSE_ID")
    private StatusServico status;

    @Column(name = "SER_DELETAVEL")
    private Boolean editavel;

    @OneToMany(mappedBy = "servico")
    private Collection<ClienteServico> clientes;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<CategoriaServico> categorias;

    public Servico(Long idServico, String codigo, String descricao, StatusServico status){
        this.setId(idServico);
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.setStatus(status);
    }

    public Servico(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Servico(Long id, String descricao, String codigo){
        this.setId(id);
        this.setDescricao(descricao);
        this.setCodigo(codigo);
    }

    public Servico(Long id, String descricao, Long idCategoria, Integer categoria, BigDecimal valor){
        this.setId(id);
        this.setDescricao(descricao);
        CategoriaServico categoriaServico = new CategoriaServico();
        categoriaServico.setId(idCategoria);
        categoriaServico.setCategoria(categoria);
        categoriaServico.setValor(valor);
    }

    public Servico(Long id, String codigo, String descricao, Boolean editavel) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.editavel = editavel;
    }

    public Servico(Long id)
    {
        this.id = id;
    }

    public void addCategoria(CategoriaServico categoriaServico)
    {
        categoriaServico.setServico(this);
        this.categorias.add(categoriaServico);
    }

    public void removeCategoria(CategoriaServico categoriaServico){
        this.categorias.remove(categoriaServico);
    }

    @PrePersist
    private void prePersist() {
        this.status = new StatusServico(StatusServico.ATIVO);
    }

    @Override
    public RestServico modelParaRest() {
        return ServicoMapper.INSTANCE.convertToRest(this);
    }
}
