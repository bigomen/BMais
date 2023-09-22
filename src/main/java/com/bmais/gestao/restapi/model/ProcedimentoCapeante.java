package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ProcedimentoCapeanteMapper;
import com.bmais.gestao.restapi.restmodel.RestProcedimentoCapeante;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "PROCEDIMENTO_CAPEANTE")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class ProcedimentoCapeante extends Model<RestProcedimentoCapeante> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCEDIMENTO_CAPEANTE")
    @SequenceGenerator(name = "SEQ_PROCEDIMENTO_CAPEANTE", sequenceName = "SEQ_PROCEDIMENTO_CAPEANTE", allocationSize = 1)
    @Column(name = "PCA_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TUS_ID")
    private Tuss tuss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAP_ID")
    private CapeanteInternacao capeante;

    @Column(name = "PCA_DATA")
    private LocalDate data;

    @Column(name = "PCA_HORA_INICIO")
    private LocalTime horaInicio;

    @Column(name = "PCA_HORA_FIM")
    private LocalTime horaFim;

    @Column(name = "PCA_PORC_PROCEDIMENTO")
    private BigDecimal porcProcedimento;

    @Override
    public RestProcedimentoCapeante modelParaRest(){
        return ProcedimentoCapeanteMapper.INSTANCE.convertToRest(this);
    }
}
