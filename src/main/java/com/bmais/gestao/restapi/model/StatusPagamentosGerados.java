package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusPagamentosGeradosMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusPagamentosGerados;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_PAGAMENTOS_GERADOS")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusPagamentosGerados extends Model<RestStatusPagamentosGerados> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SPG_ID")
    private Long id;

    @Column(name = "SPG_DESCRICAO")
    private String descricao;

    @Override
    public RestStatusPagamentosGerados modelParaRest(){
        return StatusPagamentosGeradosMapper.INSTANCE.convertToRest(this);
    }
}
