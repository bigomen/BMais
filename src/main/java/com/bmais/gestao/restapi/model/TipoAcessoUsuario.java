package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoAcessoUsuarioMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoAcessoUsuario;
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
@Table(name = "TIPO_ACESSO_USUARIO")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class TipoAcessoUsuario extends Model<RestTipoAcessoUsuario> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "TAU_ID")
    private Long id;

    @Column(name = "TAU_DESCRICAO")
    private String descricao;

    @Override
    public RestTipoAcessoUsuario modelParaRest() {
        return TipoAcessoUsuarioMapper.INSTANCE.convertToRest(this);
    }
}
