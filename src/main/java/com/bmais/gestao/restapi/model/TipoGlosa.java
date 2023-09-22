package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoGlosaMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoGlosa;
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
@Table(name = "TIPO_GLOSA")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoGlosa extends Model<RestTipoGlosa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TGL_ID")
    private Long id;

    @Column(name = "TGL_DESCRICAO")
    private String descricao;

    public TipoGlosa(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public RestTipoGlosa modelParaRest(){
        return TipoGlosaMapper.INSTANCE.convertToRest(this);
    }
}
