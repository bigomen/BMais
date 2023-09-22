package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.HospitalClienteMapper;
import com.bmais.gestao.restapi.restmodel.RestHospitalCliente;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@NoArgsConstructor
@Table(name = "PESSOA_JURIDICA_HOSPITAL")
public class HospitalCliente extends Model<RestHospitalCliente>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOA_JURIDICA_HOSPITAL")
    @SequenceGenerator(name = "SEQ_PESSOA_JURIDICA_HOSPITAL", sequenceName = "SEQ_PESSOA_JURIDICA_HOSPITAL", allocationSize = 1)
    @Column(name = "PJH_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PJH_DG")
    private Boolean dg;

    @Column(name = "PJH_CODIGO_CLIENTE")
    private String codigoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOS_ID")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SER_ID")
    private Servico servico;
    
    public HospitalCliente(Long id)
    {
    	super();
    	this.id = id;
    }

    @Override
    public RestHospitalCliente modelParaRest()
    {
        return HospitalClienteMapper.INSTANCE.toRestHospitalCliente(this);
    }
}