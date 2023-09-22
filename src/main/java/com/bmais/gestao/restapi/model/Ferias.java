package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.FeriasMapper;
import com.bmais.gestao.restapi.restmodel.RestFerias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FERIAS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Ferias extends Model<RestFerias> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FERIAS")
    @SequenceGenerator(name = "SEQ_FERIAS", sequenceName = "SEQ_FERIAS", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "FER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @Column(name = "FER_DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "FER_DATA_FIM")
    private LocalDate dataFim;

    @Column(name = "FER_DATA_LIMITE")
    private LocalDate dataLimite;

    @Column(name = "FER_GOZO_INICIO_1")
    private LocalDate gozoInicio1;

    @Column(name = "FER_GOZO_FIM_1")
    private LocalDate gozoFim1;

    @Column(name = "FER_GOZO_INICIO_2")
    private LocalDate gozoInicio2;

    @Column(name = "FER_GOZO_FIM_2")
    private LocalDate gozoFim2;

    @Column(name = "FER_GOZO_INICIO_3")
    private LocalDate gozoInicio3;

    @Column(name = "FER_GOZO_FIM_3")
    private LocalDate gozoFim3;

    @Column(name = "FER_VENDAS_DIAS")
    private Long vendasDias;

    @Column(name = "FER_QUANTIDADE_DIAS")
    private Long quantidadeDias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SFE_ID")
    private StatusFerias status;

    public Ferias(Long id, LocalDate dataInicio, LocalDate dataFim, LocalDate gozoInicio1, LocalDate gozoFim1, LocalDate gozoInicio2, LocalDate gozoFim2, LocalDate gozoInicio3, LocalDate gozoFim3, Long vendasDias, Long quantidadeDias, StatusFerias status) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.gozoInicio1 = gozoInicio1;
        this.gozoFim1 = gozoFim1;
        this.gozoInicio2 = gozoInicio2;
        this.gozoFim2 = gozoFim2;
        this.gozoInicio3 = gozoInicio3;
        this.gozoFim3 = gozoFim3;
        this.vendasDias = vendasDias;
        this.quantidadeDias = quantidadeDias;
        this.status = status;
    }

    @Override
    public RestFerias modelParaRest(){
        return FeriasMapper.INSTANCE.convertToRest(this);
    }
}
