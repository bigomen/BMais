package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoAfastamentoMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoAfastamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_AFASTAMENTO")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoAfastamento extends Model<RestTipoAfastamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "TAF_ID")
    private Long id;

    @Column(name = "TAF_DESCRICAO")
    private String descricao;

    @Override
    public RestTipoAfastamento modelParaRest(){
        return TipoAfastamentoMapper.INSTANCE.convertToRest(this);
    }
}
