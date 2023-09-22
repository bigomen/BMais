package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoComponenteMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoComponente;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_COMPONENTE")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoComponente extends Model<RestTipoComponente> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "TCO_ID")
    private Long id;

    @Column(name = "TCO_DESCRICAO")
    private String descricao;

    @Override
    public RestTipoComponente modelParaRest(){
        return TipoComponenteMapper.INSTANCE.convertToRest(this);
    }
}
