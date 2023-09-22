package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AfastamentoMapper;
import com.bmais.gestao.restapi.restmodel.RestAfastamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "AFASTAMENTO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Afastamento extends Model<RestAfastamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AFASTAMENTO")
    @SequenceGenerator(name = "SEQ_AFASTAMENTO", sequenceName = "SEQ_AFASTAMENTO", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "AFA_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "TAF_ID")
    private TipoAfastamento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CID_ID")
    private CID cid;

    @Column(name = "AFA_DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "AFA_DATA_PREVISAO")
    private LocalDate dataPrevisao;

    @Column(name = "AFA_DIAS_PREVISTOS")
    private Long diasPrevistos;

    @Column(name = "AFA_DATA_RETORNO")
    private LocalDate dataRetorno;

    @Column(name = "AFA_DIAS_REAIS")
    private Long diasReais;

    @OneToMany(mappedBy = "afastamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<Documento> documentos;

    public Afastamento(Long id, TipoAfastamento tipo, LocalDate dataInicio, LocalDate dataPrevisao, Long diasPrevistos, LocalDate dataRetorno, Long diasReais, Long idCid, String cidDescricao) {
        this.id = id;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataPrevisao = dataPrevisao;
        this.diasPrevistos = diasPrevistos;
        this.dataRetorno = dataRetorno;
        this.diasReais = diasReais;
        this.setCid(new CID(idCid, cidDescricao));
    }

    public void addDocumentos(Documento documento){
        if(this.documentos == null){
            this.documentos = new ArrayList<>();
        }
        documento.setAfastamento(this);
        documento.setDescricao(documento.getId() != null ? documento.getDescricao() : documento.getTipo() + "_" + this.colaborador.getCpf() + "_");
        this.documentos.add(documento);
    }

    @Override
    public RestAfastamento modelParaRest(){
        return AfastamentoMapper.INSTANCE.convertToRest(this);
    }
}
