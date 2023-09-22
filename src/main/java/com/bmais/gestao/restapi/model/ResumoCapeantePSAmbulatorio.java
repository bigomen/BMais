package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ResumoCapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.restmodel.RestResumoCapeantePSAmbulatorio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "RESUMO_CAPEANTE_PS_AMBULATORIO")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ResumoCapeantePSAmbulatorio extends Model<RestResumoCapeantePSAmbulatorio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESUMO_CAPEANTE_PS_AMBULATORIO")
    @SequenceGenerator(name = "SEQ_RESUMO_CAPEANTE_PS_AMBULATORIO", sequenceName = "SEQ_RESUMO_CAPEANTE_PS_AMBULATORIO", allocationSize = 1)
    @Column(name = "RCP_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPA_ID")
    private CapeantePSAmbulatorio capeantePSAmbulatorio;

    @Column(name = "RCP_PACIENTE")
    private String paciente;

    @Column(name = "RCP_VALOR_COBRADO")
    private BigDecimal valorCobrado;

    @Column(name = "RCP_VALOR_GLOSADO")
    private BigDecimal valorGlosado;

    @Column(name = "RCP_VALOR_LIBERADO")
    private BigDecimal valorLiberado;

    @Override
    public RestResumoCapeantePSAmbulatorio modelParaRest(){
        return ResumoCapeantePSAmbulatorioMapper.INSTANCE.convertToRest(this);
    }
}
