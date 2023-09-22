package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.bmais.gestao.restapi.mapper.TussMapper;
import com.bmais.gestao.restapi.restmodel.RestTuss;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TUSS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Tuss extends Model<RestTuss> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TUSS")
    @SequenceGenerator(name = "SEQ_TUSS", sequenceName = "SEQ_TUSS", allocationSize = 1)
    @Column(name = "TUS_ID")
    private Long id;

    @Column(name = "TUS_CODIGO")
    private String codigo;

    @Column(name = "TUS_DESCRICAO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "STU_ID")
    private StatusTuss status;
    
    @Override
    public RestTuss modelParaRest() {
        return TussMapper.INSTANCE.convertToRest(this);
    }

	public Tuss(Long id)
	{
		super();
		this.id = id;
	}

    public Tuss(Long id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
