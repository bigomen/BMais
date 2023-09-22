package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoAgendaMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoAgenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_AGENDA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoAgenda extends Model<RestTipoAgenda> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Long FECHAMENTO = 1L;
    private static final Long ADIANTAMENTO = 2L;

    @Id
    @Column(name = "TAG_ID")
    private Long id;

    @Column(name = "TAG_DESCRICAO")
    private String descricao;
    
    public TipoAgenda(Long id)
    {
    	super();
    	this.id = id;
    }
    
    public static TipoAgenda fechamento()
    {
    	return new TipoAgenda(FECHAMENTO);
    }
    
    public static TipoAgenda adiantamento()
    {
    	return new TipoAgenda(ADIANTAMENTO);
    }
    
    @Override
    public RestTipoAgenda modelParaRest(){
        return TipoAgendaMapper.INSTANCE.convertToRest(this);
    }
}
