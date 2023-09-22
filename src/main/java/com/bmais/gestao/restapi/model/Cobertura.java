package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.NotImplementedException;
import com.bmais.gestao.restapi.restmodel.RestCobertura;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COBERTURA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Cobertura extends Model<RestCobertura> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COBERTURA")
    @SequenceGenerator(name = "SEQ_COBERTURA", sequenceName = "SEQ_COBERTURA", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "COB_ID")
    private Long id;

    @Column(name = "COB_PERIODO_INICIO")
    private LocalDate periodoInicio;

    @Column(name = "COB_PERIODO_FIM")
    private LocalDate periodoFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUD_ID_COBERTURA", referencedColumnName = "AUD_ID")
    private Auditor auditor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVI_ID")
    private Vinculo vinculo;

    @Column(name = "COB_OBSERVACAO")
    private String observacao;

    public Cobertura(Long id) {
        this.id = id;
    }

    @Override
    public RestCobertura modelParaRest(){
    	throw new NotImplementedException("Not implemented exception");
    }
}
