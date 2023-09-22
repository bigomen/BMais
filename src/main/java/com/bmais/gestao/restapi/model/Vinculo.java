package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.VinculoAuditorMapper;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUDITOR_VINCULOS")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Vinculo extends Model<RestVinculo> implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUD_VINCULO")
    @SequenceGenerator(name = "SEQ_AUD_VINCULO", sequenceName = "SEQ_AUDITOR_VINCULOS", allocationSize = 1)
    @Column(name = "AVI_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOS_ID")
    private Hospital hospital;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUD_ID")
    private Auditor auditor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SER_ID")
    private Servico servico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSE_ID")
    private CategoriaServico categoriaServico;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vinculo")
    private Collection<Cobertura> coberturas;

    @Column(name = "AVI_DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "AVI_DATA_FIM")
    private LocalDate dataFim;
    
    public Vinculo(Long id)
    {
    	super();
    	this.id = id;
    }
    
    public Vinculo(Long id, Long idCliente, String razaoCliente, Long idHospital, String razaoHospital, Long idServico, String descricaoServico,
                   Long idCategoria, Integer categoria, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.cliente = new Cliente(idCliente, razaoCliente);
        this.hospital = new Hospital(idHospital, razaoHospital);
        this.servico = new Servico(idServico, descricaoServico);
        this.categoriaServico = new CategoriaServico(idCategoria, categoria);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Vinculo(Long id, Long idCliente, String razaoCliente, Long idAuditor, String nomeAuditor, Long idServico, String descricaoServico, String codigo){
        this.id = id;
        this.cliente = new Cliente(idCliente, razaoCliente);
        this.auditor = new Auditor(idAuditor, nomeAuditor);
        this.servico = new Servico(idServico, descricaoServico, codigo);
    }

    public Vinculo(String razaoSocial, String auditor, String descricao, String codigo){
        this.cliente = new Cliente(null, razaoSocial);
        this.auditor = new Auditor(null, auditor);
        this.servico = new Servico(null, descricao, codigo);
    }

    @Override
    public RestVinculo modelParaRest()
    {
        return VinculoAuditorMapper.INSTANCE.convertToRest(this);
    }
}






