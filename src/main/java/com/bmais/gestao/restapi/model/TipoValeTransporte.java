package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoValeTransporteMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoValeTransporte;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_VALE_TRANSPORTE")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoValeTransporte extends Model<RestTipoValeTransporte> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "TVT_ID")
    private Long id;

    @Column(name = "TVT_DESCRICAO")
    private String descricao;

    @Override
    public RestTipoValeTransporte modelParaRest(){
        return TipoValeTransporteMapper.INSTANCE.convertToRest(this);
    }
}
