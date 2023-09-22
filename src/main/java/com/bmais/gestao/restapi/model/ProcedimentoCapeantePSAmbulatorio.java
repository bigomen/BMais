package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.ProcedimentoCapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.restmodel.RestProcedimentoCapeantePSAmbulatorio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PROCEDIMENTO_REALIZADO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class ProcedimentoCapeantePSAmbulatorio extends Model<RestProcedimentoCapeantePSAmbulatorio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCEDIMENTO_REALIZADO")
    @SequenceGenerator(name = "SEQ_PROCEDIMENTO_REALIZADO", sequenceName = "SEQ_PROCEDIMENTO_REALIZADO", allocationSize = 1)
    @Column(name = "PRE_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPA_ID")
    private CapeantePSAmbulatorio capeantePSAmbulatorio;

    @Column(name = "PRE_NOME_PACIENTE")
    private String nomePaciente;

    @Column(name = "PRE_VALOR_COBRADO")
    private BigDecimal valorCobrado;

    @Column(name = "PRE_VALOR_GLOSADO")
    private BigDecimal valorGlosado;

    @Column(name = "PRE_VALOR_LIBERADO")
    private BigDecimal valorLiberado;

    public ProcedimentoCapeantePSAmbulatorio(Long id, String nomePaciente, BigDecimal valorCobrado, BigDecimal valorGlosado, BigDecimal valorLiberado){
        this.setId(id);
        this.setNomePaciente(nomePaciente);
        this.setValorCobrado(valorCobrado);
        this.setValorGlosado(valorGlosado);
        this.setValorLiberado(valorLiberado);
    }

    @Override
    public RestProcedimentoCapeantePSAmbulatorio modelParaRest(){
        return ProcedimentoCapeantePSAmbulatorioMapper.INSTANCE.convertToRest(this);
    }
}
