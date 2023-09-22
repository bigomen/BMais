package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusProdutividadeMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusProdutividade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_PRODUTIVIDADE")
@Immutable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusProdutividade extends Model<RestStatusProdutividade> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final long ATIVO = 1L;
    public static final long INATIVO = 2L;
    public static final long ATIVO_FECHADO = 3L;
    public static final long INATIVO_FECHADO = 4L;

    @Id
    @Column(name = "SPR_ID")
    private Long id;

    @Column(name = "SPR_DESCRICAO")
    private String descricao;

    @Override
    public RestStatusProdutividade modelParaRest(){
        return StatusProdutividadeMapper.INSTANCE.convertToRest(this);
    }
}
