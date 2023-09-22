package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.ColaboradorBeneficioMapper;
import com.bmais.gestao.restapi.restmodel.RestColaboradorBeneficio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "COLABORADOR_BENEFICIO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ColaboradorBeneficio extends Model<RestColaboradorBeneficio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLABORADOR_BENEFICIO")
    @SequenceGenerator(name = "SEQ_COLABORADOR_BENEFICIO", sequenceName = "SEQ_COLABORADOR_BENEFICIO", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "COB_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEP_ID")
    private Dependente dependente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEN_ID")
    private Beneficio beneficio;

    @Column(name = "COB_DATA_ADESAO")
    private LocalDate dataAdesao;

    @Column(name = "COB_DATA_FIM")
    private LocalDate dataFim;

    @Column(name = "COB_VALOR_OFERECIDO")
    private BigDecimal valorOferecido;

    @Column(name = "COB_VALOR_CONTRATADO")
    private BigDecimal valorContratado;

    @Column(name = "COB_VALOR_DESCONTADO")
    private BigDecimal valorDescontado;

    @Column(name = "COB_OBSERVACAO")
    private String observacao;

    public ColaboradorBeneficio(Long id, Long idBeneficio, String descricaoBeneficio, LocalDate dataAdesao, LocalDate dataFim, BigDecimal valorOferecido, BigDecimal valorContratado, BigDecimal valorDescontado, String observacao){
        this.id = id;
        this.beneficio = new Beneficio(idBeneficio, descricaoBeneficio);
        this.dataAdesao = dataAdesao;
        this.dataFim = dataFim;
        this.valorOferecido = valorOferecido;
        this.valorContratado = valorContratado;
        this.valorDescontado = valorDescontado;
        this.observacao = observacao;
    }

    @Override
    public RestColaboradorBeneficio modelParaRest(){
        return ColaboradorBeneficioMapper.INSTANCE.convertToRest(this);
    }
}
